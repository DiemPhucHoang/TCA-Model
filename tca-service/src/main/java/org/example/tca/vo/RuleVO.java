package org.example.tca.vo;

import org.example.tca.api.Rule;

import java.util.List;

public class RuleVO {
    private Long id;
    private String conditionLogicalOperator;
    private String aggregator;
    private String aggregationPeriod;
    private List<ConditionVO> conditions;
    private int noOfConditions;
    private AlarmVO alarm;
    private Long alarmId;

    public RuleVO() {
    }

    public RuleVO(Rule rule) {
        this.id = rule.getId();
        this.conditionLogicalOperator = rule.getConditionLogicalOperator().getKey();
        this.aggregator = rule.getAggregator().getKey();
        this.aggregationPeriod = rule.getAggregationPeriod();
        this.noOfConditions = rule.getConditions() == null ? 0 : rule.getConditions().size();
        this.alarmId = rule.getAlarm() == null ? null :rule.getAlarm().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
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
