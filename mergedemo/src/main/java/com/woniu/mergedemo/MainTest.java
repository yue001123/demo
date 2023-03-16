package com.woniu.mergedemo;


import java.util.*;

/**
 * Java 8 中 Map的一个骚操作！好用到爆！！
 *
 * 假设我们有这么一段业务逻辑，
 * 我有一个学生成绩对象的列表，
 * 对象包含学生姓名、科目、科目分数三个属性，
 * 要求求得每个学生的总成绩。
 */
public class MainTest {

    public static void main(String[] args) {
        List<StudentScore> studentScoreList = Arrays.asList(
                new StudentScore("张三", "语文", 70),
                new StudentScore("张三", "数学", 70),
                new StudentScore("张三", "英语", 65),
                new StudentScore("李四", "语文", 68),
                new StudentScore("李四", "数学", 70),
                new StudentScore("李四", "英语", 90),
                new StudentScore("王五", "语文", 80),
                new StudentScore("王五", "数学", 85),
                new StudentScore("王五", "英语", 70)
        );

        Map<String, Integer> studentScoreMap = new HashMap<>();
        studentScoreList.forEach(studentScore -> {
            if (studentScoreMap.containsKey(studentScore.getStuName())) {
                studentScoreMap.put(studentScore.getStuName(), studentScoreMap.get(studentScore.getStuName()) + studentScore.getScore());
            } else {
                studentScoreMap.put(studentScore.getStuName(), studentScore.getScore());
            }
        });


        Map<String, Integer> studentScoreMap2 = new HashMap<>();

        studentScoreList.forEach(studentScore -> studentScoreMap2.merge(
                studentScore.getStuName(),
                studentScore.getScore(),
                (oldValue, newValue) -> oldValue + newValue));
        //merge() 方法会先判断指定的 key 是否存在，如果不存在，则添加键值对到 hashMap 中。


    }



}
