package org.example.tca.api;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "Rule")
@Table (name = "tca_rule")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "condition_logical_operator")
    private ConditionLogicalOperator conditionLogicalOperator;

    @Column(name = "aggregator")
    private Aggregation aggregator;

    @Column(name = "aggregation_period")
    private String aggregationPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "threshold_id", nullable = false)
    private Threshold threshold;

    @OneToMany(mappedBy = "rule", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Condition> conditions = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Alarm alarm;

    public Rule() {
    }

    public Rule(ConditionLogicalOperator conditionLogicalOperator, Aggregation aggregator, String aggregationPeriod) {
        this.conditionLogicalOperator = conditionLogicalOperator;
        this.aggregator = aggregator;
        this.aggregationPeriod = aggregationPeriod;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ConditionLogicalOperator getConditionLogicalOperator() {
        return conditionLogicalOperator;
    }

    public void setConditionLogicalOperator(ConditionLogicalOperator conditionLogicalOperator) {
        this.conditionLogicalOperator = conditionLogicalOperator;
    }

    public Aggregation getAggregator() {
        return aggregator;
    }

    public void setAggregator(Aggregation aggregator) {
        this.aggregator = aggregator;
    }

    public String getAggregationPeriod() {
        return aggregationPeriod;
    }

    public void setAggregationPeriod(String aggregationPeriod) {
        this.aggregationPeriod = aggregationPeriod;
    }

    public Threshold getThreshold() {
        return threshold;
    }

    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }
}
