package org.example.tca.vo;

import org.example.tca.api.Rule;

import java.util.List;

public class RuleVO {
    private String conditionLogicalOperator;
    private String aggregator;
    private String aggregationPeriod;
    private List<ConditionVO> conditions;
    private int noOfConditions;
    private AlarmVO alarm;

    public RuleVO() {
    }

    public RuleVO(Rule rule) {
        this.conditionLogicalOperator = rule.getConditionLogicalOperator().getKey();
        this.aggregator = rule.getAggregator().getKey();
        this.aggregationPeriod = rule.getAggregationPeriod();
    }

    public String getConditionLogicalOperator() {
        return conditionLogicalOperator;
    }

    public void setConditionLogicalOperator(String conditionLogicalOperator) {
        this.conditionLogicalOperator = conditionLogicalOperator;
    }

    public String getAggregator() {
        return aggregator;
    }

    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    public String getAggregationPeriod() {
        return aggregationPeriod;
    }

    public void setAggregationPeriod(String aggregationPeriod) {
        this.aggregationPeriod = aggregationPeriod;
    }

    public List<ConditionVO> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionVO> conditions) {
        this.conditions = conditions;
    }

    public int getNoOfConditions() {
        return noOfConditions;
    }

    public void setNoOfConditions(int noOfConditions) {
        this.noOfConditions = noOfConditions;
    }

    public AlarmVO getAlarm() {
        return alarm;
    }

    public void setAlarm(AlarmVO alarm) {
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return "RuleVO{" +
                "conditionLogicalOperator='" + conditionLogicalOperator + '\'' +
                ", aggregator='" + aggregator + '\'' +
                ", aggregationPeriod='" + aggregationPeriod + '\'' +
                '}';
    }
}
