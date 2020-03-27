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
import javax.persistence.Table;
import java.io.Serializable;

@Entity (name = "Condition")
@Table (name = "tca_condition")
public class Condition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "attribute_name", length = 128)
    private String attributeName;

    @Column(name = "attribute_gui_name",length = 128)
    private String attributeGuiName;

    @Column(name = "object_type", length = 128)
    private String objectType;

    @Column(name = "operator", nullable = false)
    private Operator operator;

    @Column(name = "default_value", nullable = false)
    private Long defaultValue;

    @Column(name = "clear_operator")
    private Operator clearOperator;

    @Column(name = "clear_default_value")
    private Long clearDefaultValue;

    @Column(name = "rate")
    private Boolean rate;

    @Column(name = "counter_max")
    private Double counterMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", nullable = false)
    private Rule rule;

    public Condition() {
    }

    public Condition(String attributeName, String attributeGuiName, String objectType, Operator operator,
                     Long defaultValue, Operator clearOperator, Long clearDefaultValue, Boolean rate, Double counterMax) {
        this.attributeName = attributeName;
        this.attributeGuiName = attributeGuiName;
        this.objectType = objectType;
        this.operator = operator;
        this.defaultValue = defaultValue;
        this.clearOperator = clearOperator;
        this.clearDefaultValue = clearDefaultValue;
        this.rate = rate == null ? false : rate;
        this.counterMax = counterMax == null ? Double.valueOf("18446744073709551615") : counterMax;
    }

    public Condition(String attributeName, String attributeGuiName, String objectType, Operator operator, Long defaultValue,
                     Operator clearOperator, Long clearDefaultValue, Boolean rate, Double counterMax, Rule rule) {
        this.attributeName = attributeName;
        this.attributeGuiName = attributeGuiName;
        this.objectType = objectType;
        this.operator = operator;
        this.defaultValue = defaultValue;
        this.clearOperator = clearOperator;
        this.clearDefaultValue = clearDefaultValue;
        this.rate = rate == null ? false : rate;
        this.counterMax = counterMax == null ? Double.valueOf("18446744073709551615") : counterMax;
        this.rule = rule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeGuiName() {
        return attributeGuiName;
    }

    public void setAttributeGuiName(String attributeGuiName) {
        this.attributeGuiName = attributeGuiName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Long getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Long defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Operator getClearOperator() {
        return clearOperator;
    }

    public void setClearOperator(Operator clearOperator) {
        this.clearOperator = clearOperator;
    }

    public Long getClearDefaultValue() {
        return clearDefaultValue;
    }

    public void setClearDefaultValue(Long clearDefaultValue) {
        this.clearDefaultValue = clearDefaultValue;
    }

    public Boolean getRate() {
        return rate;
    }

    public void setRate(Boolean rate) {
        this.rate = rate;
    }

    public Double getCounterMax() {
        return counterMax;
    }

    public void setCounterMax(Double counterMax) {
        this.counterMax = counterMax;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}