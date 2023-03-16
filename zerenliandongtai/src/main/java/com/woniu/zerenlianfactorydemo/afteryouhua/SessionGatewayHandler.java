package com.woniu.zerenlianfactorydemo.afteryouhua;

public class SessionGatewayHandler extends GatewayHandler {

    @Override
    public void service() {
        System.out.println("用户会话拦截");
        if (this.next != null) {
            this.next.service();
        }
    }
}
