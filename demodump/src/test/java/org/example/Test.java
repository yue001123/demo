package org.example;

import org.example.function.VUtils;

/**
 * 用Java 8中的 Function接口，消灭代码中if...else
 *
 * Supplier供给型函数
 * Consumer消费型函数
 * Runnable无参无返回型函数
 * Function函数的表现形式为接收一个参数，并返回一个值。
 * Supplier、Consumer和Runnable可以看作Function的一种特殊表现形式
 *
 * 处理抛出异常的if
 * 处理if分支操作
 * 如果存在值执行消费操作，否则执行基于空的操作
 */
public class Test {


    public static void main(String[] args) {
//        if (...){
//            throw new RuntimeException("出现异常了")；
//        }
//
//        if (...){
//            doSomething();
//        } else {
//            doOther();
//        }

//        VUtils.isTure(true).throwMessage("hahaha");


//        VUtils.isTureOrFalse(false)
//                .trueOrFalseHandle(
//                        () -> {
//                            System.out.println("我是true ，现在我要这样做");
//                        },
//                        () -> {
//                            System.out.println("我是false ，天下无敌");
//                        }
//                );

        VUtils.isBlankOrNoBlank("hello")
                .presentOrElseHandle(System.out::println,
                        () -> {
                            System.out.println("空字符串");
                        });


    }
}
