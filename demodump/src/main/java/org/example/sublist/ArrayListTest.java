package org.example.sublist;


import java.util.Arrays;
import java.util.List;

/**
 * 数组测试
 *
 * @author xiaomage
 * @date 2022/12/25
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<Integer> statusList = Arrays.asList(1, 2);
        System.out.println(statusList);
        System.out.println(statusList.contains(1));
        System.out.println(statusList.contains(3));


        statusList.add(3);
        System.out.println(statusList.contains(3));





    }

}
