package com.woniu.zerenlianfactorydemo.before;

import com.woniu.zerenlianfactorydemo.afteryouhua.ApiLimitGatewayHandler;
import com.woniu.zerenlianfactorydemo.afteryouhua.BlacklistGatewayHandler;
import com.woniu.zerenlianfactorydemo.afteryouhua.GatewayHandler;
import com.woniu.zerenlianfactorydemo.afteryouhua.SessionGatewayHandler;

/**
 * 我们可以通过链表将每一关连接起来，
 * 形成责任链的方式
 */
public class GetewayClientBefore {
    public static void main(String[] args) {
       //api接口限流  黑名单拦截  用户会话拦截

        GatewayHandler firstPassHandler = new ApiLimitGatewayHandler();
        GatewayHandler secondPassHandler = new BlacklistGatewayHandler();
        GatewayHandler thirdPassHandler = new SessionGatewayHandler();

        firstPassHandler.setNext(secondPassHandler);
        secondPassHandler.setNext(thirdPassHandler);

        firstPassHandler.service();

    }
}
