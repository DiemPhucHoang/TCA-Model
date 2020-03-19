package org.example.tca.vo;

import org.example.tca.api.Rule;

public class RuleVO {
    private String conditionLogicalOperator;
    private String aggregator;
    private String aggregationPeriod;

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

    @Override
    public String toString() {
        return "RuleVO{" +
                "conditionLogicalOperator='" + conditionLogicalOperator + '\'' +
                ", aggregator='" + aggregator + '\'' +
                ", aggregationPeriod='" + aggregationPeriod + '\'' +
                '}';
    }
}
