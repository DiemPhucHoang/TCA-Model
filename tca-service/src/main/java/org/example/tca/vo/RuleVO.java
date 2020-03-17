package org.example.tca.vo;

import org.example.tca.api.Aggregation;
import org.example.tca.api.ConditionLogicalOperator;
import org.example.tca.api.Rule;

public class RuleVO {
    private ConditionLogicalOperator conditionLogicalOperator;
    private Aggregation aggregation;
    private String aggregationPeriod;

    public RuleVO(Rule rule) {
        this.conditionLogicalOperator = rule.getConditionLogicalOperator();
        this.aggregation = rule.getAggregator();
        this.aggregationPeriod = rule.getAggregationPeriod();
    }

    public ConditionLogicalOperator getConditionLogicalOperator() {
        return conditionLogicalOperator;
    }

    public void setConditionLogicalOperator(ConditionLogicalOperator conditionLogicalOperator) {
        this.conditionLogicalOperator = conditionLogicalOperator;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    public void setAggregation(Aggregation aggregation) {
        this.aggregation = aggregation;
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
                "conditionLogicalOperator=" + conditionLogicalOperator +
                ", aggregation=" + aggregation +
                ", aggregationPeriod='" + aggregationPeriod + '\'' +
                '}';
    }
}
