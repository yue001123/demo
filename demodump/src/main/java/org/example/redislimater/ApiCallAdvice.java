/*
 * Copyright (C), 2019-2020 dingyong
 * FileName: ApiCallAdvice
 * Author:   dingyong
 * Date:     2021/1/22
 * Description: //描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.example.redislimater;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.redislimater.exception.ApiCallException;
import org.example.redislimater.exception.BusinessException;
import org.example.redislimater.utils.IpUtils;
import org.example.redislimater.utils.Recode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dingyong
 * @Description: 接口调用情况监控<br/>
 *              1、监控单个接口一天内的调用次数<br/>
 *              2、如果抛出异常，则记录异常信息及发生时间<br/>
 *              3、对单个IP进行限流，每天对每个接口的调用次数有限<br/>
 * @Date 2021/1/22
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Aspect
@Component
public class ApiCallAdvice {

    private static final Logger interfaceLog = LoggerFactory.getLogger("interfaceLog");

    @Resource
    private RedisUtils redisUtils;

    private static final String FORMAT_PATTERN_DAY = "yyyy-MM-dd";
    private static final String FORMAT_PATTERN_MILLS = "yyyy-MM-dd HH:mm:ss:SSS";

    // 配置织入点
    @Pointcut("@annotation(org.example.redislimater.ApiCall)")
    public void apiCall()
    {
    }

    /**
     *
     * @param pjp
     * @return
     */
    @Around(value = "apiCall())")
    public Object requestLimitAround(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        ApiCall apiCall = methodSignature.getMethod().getAnnotation(ApiCall.class);
        // 限制访问次数
        long limitCount = apiCall.limitCount();

        // 获取 request ， 然后获取访问 ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestIp = IpUtils.getIpAddr(request);
        if(StringUtils.isEmpty(requestIp)) {
            interfaceLog.error("execute api {} error, ip is empty",methodSignature.getMethod().getName());
            throw new ApiCallException(Recode.IP_ERROR.getCode(),Recode.IP_ERROR.getDesc());
        }

        //获取uri
        String uri = request.getRequestURI();
        String key = "API_LIMIT:" + uri + "_" + requestIp +"_";
        int size = redisUtils.keys(key + "*").size();
        if( size > limitCount ){
            interfaceLog.error("execute api {} error, ip exceed max call limit!, IP is {}",methodSignature.getMethod().getName(),requestIp);
            throw new ApiCallException(Recode.LIMIT_ERROR.getCode(),Recode.LIMIT_ERROR.getDesc());
        }

        // 将访问存进缓存
        redisUtils.set(key+System.currentTimeMillis(), "1", apiCall.time(), apiCall.timeUnit());

        //获取传入目标方法的参数
        Object[] args = pjp.getArgs();
        try {
            // 执行访问并返回数据
            //方法参数
            String param;
            if (args.length == 1){
                param = JSON.toJSONString(args[0]);
            }else{
                param = Arrays.toString(pjp.getArgs());
            }
            //耗时
            long beginTime = System.nanoTime();
            //执行结果
            Object result = pjp.proceed(args);
            long endTime =System.nanoTime();
            String resultJson = JSON.toJSONString(result);
            //日志打印
            interfaceLog.info("execute api : {},param: {},result:{},cost：{}ms",
                    methodSignature.getMethod().getName(),param,resultJson,(endTime-beginTime)/1000000);
            return result;
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            //interfaceLog.error("execute api:{}error, cause by {}", methodSignature.getMethod().getName(),throwable.getMessage());
            interfaceLog.error("execute api:{}error, cause by {}, ip is{}", methodSignature.getMethod().getName(),throwable.getMessage(),requestIp);
            throw new BusinessException("系统异常");
        }
    }

    /**
     * 真正执行业务操作前先进行总的限流的验证
     * 限制维度为：一天内单个IP的访问次数
     * key = URI + IP + date（精确到天）
     * value = 调用次数
     */
    @Before("apiCall()")
    public void before() {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求的request
        HttpServletRequest request = attributes.getRequest();

        String uri = request.getRequestURI();
        String date = dateFormat(FORMAT_PATTERN_DAY);
        String ip = IpUtils.getIpAddr(request);

        if (StringUtils.isEmpty(ip)) {
            interfaceLog.error("execute uri {} error, Unauthorized access！ip cannot empty！",uri);
            throw new ApiCallException(Recode.IP_ERROR.getCode(),Recode.IP_ERROR.getDesc());
        }
        // URI+IP+日期 构成以天为维度的key
        String ipKey = "API_LIMIT_DAY:"+uri + "_" + ip + "_" + date;
        if (redisUtils.hasKey(ipKey)) {
            if (Integer.parseInt(redisUtils.get(ipKey).toString()) > 10000) {
                interfaceLog.error("execute uri {} error, exceed max call limit！,IP is :{}",uri,ip);
                throw new ApiCallException(Recode.LIMIT_ERROR.getCode(),Recode.LIMIT_ERROR.getDesc());
            }
            redisUtils.incr(ipKey, 1);
        } else {
            redisUtils.set(ipKey, 1, 1L, TimeUnit.DAYS);
        }
    }

    /**
     * 如果有返回结果，代表一次调用，则对应接口的调用次数加一，统计维度为天
     * （Redis使用Hash结构）
     * key = URI
     * key = date （精确到天）
     * value = 调用次数
     */
    @AfterReturning("apiCall()")
    public void afterReturning() {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求的request
        HttpServletRequest request = attributes.getRequest();

        String uri = request.getRequestURI();
        String date = dateFormat(FORMAT_PATTERN_DAY);
        if (redisUtils.hasKey(uri)) {
            redisUtils.boundHashOpsIncrement(uri,date, 1);
        } else {
            redisUtils.boundHashOpsPut(uri,date, 1);
        }
    }

    /**
     * 如果调用抛出异常，则缓存异常信息（Redis使用Hash结构）
     * key = URI + “_exception”
     * key = time (精确到毫秒的时间)
     * value = exception 异常信息
     *
     * @param ex 异常信息
     */
    @AfterThrowing(value = "apiCall()", throwing = "ex")
    public void afterThrowing(Exception ex) {
        //ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // HttpServletRequest request = attributes.getRequest();
        //String uri = request.getRequestURI() + "_exception";
        //String time = dateFormat(FORMAT_PATTERN_MILLS);
        //redisTemplate.boundHashOps(uri).put(time, exception);
        interfaceLog.error("execute api error, cause by {}",ex.getMessage());
    }


    private String dateFormat(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }

}
