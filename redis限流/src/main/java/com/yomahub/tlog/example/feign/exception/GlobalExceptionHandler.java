package com.yomahub.tlog.example.feign.exception;
import com.yomahub.tlog.example.feign.vo.ResultInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice// 声明这个类为全局异常处理器
public class GlobalExceptionHandler {


    @ExceptionHandler(RedisLimitException.class) // 声明当前方法要处理的异常类型
    public ResultInfo handlerCustomException(RedisLimitException e) {
        //1. 打印日志
//        e.printStackTrace();

        //2. 给前端提示
        return ResultInfo.error(e.getMessage());
    }


    //非预期异常 对于他们，我们直接捕获，捕获完了，记录日志， 给前端一个假提示
    @ExceptionHandler(Exception.class)
    public ResultInfo handlerException(Exception e) {
        //1. 打印日志
        e.printStackTrace();

        //2. 给前端提示
        return ResultInfo.error("当前系统异常");
    }
}
