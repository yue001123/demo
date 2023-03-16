package com.woniu;


import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 面试官：怎么去除 List 中的重复元素？一行代码搞定，赶紧拿去用！
 */
public class TestList {

    /**
     * 3 个张三，2 个李强
     */
    public List<String> initList = Arrays.asList(
            "张三",
            "李四",
            "张三",
            "周一",
            "刘四",
            "李强",
            "李白",
            "张三",
            "李强",
            "王五"
    );

    /**
     * for 循环添加去重
     *
     * @author: 蜗牛
     */
    public void remove1() {
        List<String> list = new ArrayList(initList);
        List<String> list2 = new ArrayList<>();
        for (String element : list) {
            if (!list2.contains(element)) {
                list2.add(element);
            }
        }
    }


    /**
     * for 双循环去重
     * @author: 蜗牛
     */
    public void remove2() {
        List<String> list = new ArrayList(initList);
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        System.out.println(list);
    }

    /**
     * for 循环重复坐标去重
     * @author: 蜗牛
     */
    public void remove3() {
        List<String> list = new ArrayList(initList);
        List<String> list2 = new ArrayList(initList);
        for (String element : list2) {
            if (list.indexOf(element) != list.lastIndexOf(element)) {
                list.remove(list.lastIndexOf(element));
            }
        }
        System.out.println(list);
    }


    /**
     * Set 去重
     * @author: 蜗牛
     */
    public void remove4() {
        List<String> list = new ArrayList(initList);
        List<String> list2 = new ArrayList(new HashSet(list));
        System.out.println(list2);
    }


    /**
     * Set 去重
     * @author: 蜗牛
     */
    @Test
    public void remove5() {
        List<String> list = new ArrayList(initList);
        List<String> list2 = new ArrayList(new LinkedHashSet(list));
        System.out.println(list2);
    }



    /**
     * Stream 去重
     * @author: 蜗牛
     */
    public void remove6() {
        List<String> list = new ArrayList(initList);
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);
    }

}
