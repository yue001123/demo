package org.example.haoshi;


import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * 使用 StopWatch 优雅打印执行耗时
 *
 * @author xiaomage
 * @date 2022/12/24
 */
public class StopWatchTest {


    public static void main(String[] args) throws InterruptedException {


//        long start = System.currentTimeMillis();
//        // do something
//        Thread.sleep(100);
//        long end = System.currentTimeMillis();
//        long start2 = System.currentTimeMillis();
//        // do something
//        Thread.sleep(200);
//        long end2 = System.currentTimeMillis();
//        System.out.println("某某1执行耗时:" + (end - start));
//        System.out.println("某某2执行耗时:" + (end2 - start2));


//        StopWatch sw = new StopWatch();
//        sw.start();
//        Thread.sleep(1000);
//        sw.stop();
//        System.out.println(sw.getTotalTimeMillis());


        StopWatch sw = new StopWatch();
        sw.start("A");
        Thread.sleep(500);
        sw.stop();
        sw.start("B");
        Thread.sleep(300);
        sw.stop();
        sw.start("C");
        Thread.sleep(200);
        sw.stop();
        System.out.println(sw.prettyPrint());

//        一个StopWatch实例一次只能开启一个task，不能同时start多个task，
//        并且在该task未stop之前不能start一个新的task，
//        必须在该task stop之后才能开启新的task，若要一次开启多个，需要new不同的StopWatch实例；
    }

    private static void getTasksTime() throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start("A");
        Thread.sleep(500);
        sw.stop();
        sw.start("B");
        Thread.sleep(300);
        sw.stop();
        sw.start("C");
        Thread.sleep(200);
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

}
