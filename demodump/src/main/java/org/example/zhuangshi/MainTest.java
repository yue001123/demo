package org.example.zhuangshi;


public class MainTest {

    public static void main(String[] args) {
        ISurfTheInternetService retroInternetBarService = new RetroInternetBar();
////
////        fadInternetCafeService.doSurfing();
////        retroInternetBarService.doSurfing();
//
//
//
//
//        SurfDecorator retroInternetBarDecoratorService = new SurfDecorator(retroInternetBarService);
//        retroInternetBarDecoratorService.doSurfing();

        //先装一哈
        SurfDecorator retroInternetBarDecoratorService = new SurfDecorator(retroInternetBarService);
        //再包装一哈
        RechargeDecorator rechargeDecorator = new RechargeDecorator(retroInternetBarDecoratorService);
        rechargeDecorator.doSurfing();

    }
}
