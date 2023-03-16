package org.example.zhuangshi;



/**
 * @Author: xiaomage
 * @Date: 2022-10-07 15:29
 * @Description:
 */
public class RechargeDecorator extends SurfDecorator{

    public RechargeDecorator(ISurfTheInternetService surfTheInternetService) {
        super(surfTheInternetService);
    }

    @Override
    public void doSurfing() {
        super.doSurfing();
        checkRecharge();
    }
    private void checkRecharge(){
        System.out.print("RechargeDecorator 也在增强,看看这个货卡里面充了有多少,就来上网");
    }

}