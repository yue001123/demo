package com.woniu.roledemo;


import java.util.Arrays;

/**
 *
 * 还在写大量 if 来判断？试试用一个规则执行器神器吧！
 *
 * if (是否海外用户) {
 * return false;
 * }
 *
 * if (刷单用户) {
 * return false;
 * }
 *
 * if (未付费用户 && 不再服务时段) {
 * return false
 * }
 *
 * if (转介绍用户 || 付费用户 || 内推用户) {
 * return true;
 * }
 */
public class RuleServiceTest {

    public static void main(String[] args) {
        //规则执行器
        //优点：比较简单，每个规则可以独立，将规则，数据，执行器拆分出来，调用方比较规整
        //缺点：数据依赖公共传输对象 dto

        //1. 定义规则  init rule
        NationalityRule nationalityRule = new NationalityRule();
        AddressRule addressRule = new AddressRule();
        NameRule nameRule = new NameRule();

        //2. 构造需要的数据 create dto
        RuleDto dto = new RuleDto();
        dto.setAge(5);
        dto.setName("haha");
        dto.setAddress("南京");

        //3. 通过以链式调用构建和执行 rule execute
        boolean ruleResult = RuleService
                .create()
                .and(Arrays.asList(nationalityRule, addressRule))
                .or(Arrays.asList(nameRule))
                .execute(dto);
        System.out.println("this rule execute result :" + ruleResult);
    }
}
