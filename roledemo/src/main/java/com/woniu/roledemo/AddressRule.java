package com.woniu.roledemo;


// 具体规则- 例子1
public class AddressRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke!");
        if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START)) {
            return true;
        }
        return false;
    }
}
