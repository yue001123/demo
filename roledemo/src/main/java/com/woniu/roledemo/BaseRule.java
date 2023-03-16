package com.woniu.roledemo;

// 规则抽象
public interface BaseRule {
    boolean execute(RuleDto dto);
}
