package com.woniu.roledemo;


public class NameRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("NameRule invoke!");
        if (dto.getName().startsWith("woniu")) {
            return true;
        }
        return false;
    }
}
