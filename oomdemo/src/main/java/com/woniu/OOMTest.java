package com.woniu;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出的参数
 * Eclipse Memory Analyzer分析内存溢出
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\woniu
 *
 *1.占用内存过大的对象有哪些 （Histogram）
 *2.被谁引用的 （dominator_tree）
 *3.定位到具体的代码 （thread_overview）
 *
 * 排查的过程都很简单，难的是如何解决，如果代码是自己写的，那一会就改完了，
 * 如果是一些中间件的代码造成的内存溢出就要求你对中间件的实现有个基本的了解才能解决！
 */
public class OOMTest {


    public static void main(String[] args) {
        List<byte[]> memoryLeakArray = new ArrayList<byte[]>();
        for (int i = 0; i < 1024; i++) {
            byte[] tmp = new byte[1024 * 1024]; // 添加1M的数据到List中
            memoryLeakArray.add(tmp);  //warning： 这里会造成OOM
        }
    }

}
