package com.woniu.zerenlianfactorydemo.afteryouhua;

/**
 * 我们也可以把这个关系维护到配置文件中或者一个枚举中。
 */
public enum GatewayEnum {
    // handlerId, 拦截者名称，全限定类名，preHandlerId，nextHandlerId
    API_HANDLER(new GatewayEntity(1, "api接口限流", "com.woniu.zerenlianfactorydemo.afteryouhua.ApiLimitGatewayHandler", null, 2)),
    BLACKLIST_HANDLER(new GatewayEntity(2, "黑名单拦截", "com.woniu.zerenlianfactorydemo.afteryouhua.BlacklistGatewayHandler", 1, 3)),
    SESSION_HANDLER(new GatewayEntity(3, "用户会话拦截", "com.woniu.zerenlianfactorydemo.afteryouhua.SessionGatewayHandler", 2, null)),
    ;

    GatewayEntity gatewayEntity;

    public GatewayEntity getGatewayEntity() {
        return gatewayEntity;
    }

    GatewayEnum(GatewayEntity gatewayEntity) {
        this.gatewayEntity = gatewayEntity;
    }
}







