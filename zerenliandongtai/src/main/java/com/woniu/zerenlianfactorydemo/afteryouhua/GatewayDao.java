package com.woniu.zerenlianfactorydemo.afteryouhua;

/**
 * @className: GatewayDao
 * @author: woniuge
 * @date: 2023/2/2
 **/
public interface GatewayDao {

    /**
     * 根据 handlerId 获取配置项
     *
     * @param handlerId
     * @return
     */
    GatewayEntity getGatewayEntity(Integer handlerId);

    /**
     * 获取第一个处理者
     *
     * @return
     */
    GatewayEntity getFirstGatewayEntity();
}