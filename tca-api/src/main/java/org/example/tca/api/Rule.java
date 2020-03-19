package org.example.tca.api;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "Rule")
@Table (name = "tca_rule")
public class Rule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "condition_logical_operator")
    private ConditionLogicalOperator conditionLogicalOperator;

    @Column(name = "aggregator")
    private Aggregator aggregator;

    @Column(name = "aggregation_period")
    private String aggregationPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "threshold_id", nullable = false)
    private Threshold threshold;

    @OneToMany(mappedBy = "rule", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Condition> conditions = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rule")
    private Alarm alarm;

    public Rule() {
    }

    // Set default some fields if null
    public Rule(ConditionLogicalOperator conditionLogicalOperator, Aggregator aggregator, String aggregationPeriod) {
        this.conditionLogicalOperator = conditionLogicalOperator == null ? ConditionLogicalOperator.and : conditionLogicalOperator;
        this.aggregator = aggregator == null ? Aggregator.avg : aggregator;
        this.aggregationPeriod = aggregationPeriod == null ? "PT6H" : aggregationPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionLogicalOperator getConditionLogicalOperator() {
        return conditionLogicalOperator;
    }

    public void setConditionLogicalOperator(ConditionLogicalOperator conditionLogicalOperator) {
        this.conditionLogicalOperator = conditionLogicalOperator;
    }

    public Aggregator getAggregator() {
        return aggregator;
    }

    public void setAggregator(Aggregator aggregator) {
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
