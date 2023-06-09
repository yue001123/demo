package com.woniu.celuejj.celue;


public enum StrategySelector {

    strategyA(1, "strategyA"),
    strategyB(2, "strategyB"),
    strategyC(3, "strategyC");

    private Integer code;
    private String strategy;

    public String getStrategy() {
        return strategy;
    }

    public Integer getCode() {
        return code;
    }

    StrategySelector(Integer code, String strategy) {
        this.code = code;
        this.strategy = strategy;
    }
}

