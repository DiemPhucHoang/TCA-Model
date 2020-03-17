package org.example.tca.api;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity (name = "Condition")
@Table (name = "tca_condition")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "attribute_name", length = 128, unique = true)
    private String attributeName;

    @Column(name = "attribute_gui_name",length = 128)
    private String attributeGuiName;

    @Column(name = "object_type", length = 128)
    private String objectType;

    @Column(name = "operator", nullable = false)
    private Operator operator;

    @Column(name = "value", nullable = false)
    private Long value;

    @Column(name = "clear_operator")
    private Operator clearOperator;

    @Column(name = "clear_value")
    private Long clearValue;

    @Column(name = "rate")
    private Boolean rate;

    @Column(name = "counter_max")
    @ColumnDefault("18446744073709551615")
    private Double counterMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", nullable = false)
    private Rule rule;

    public Condition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Operator getClearOperator() {
        return clearOperator;
    }

    public void setClearOperator(Operator clearOperator) {
        this.clearOperator = clearOperator;
    }

    public Long getClearValue() {
        return clearValue;
    }

    public void setClearValue(Long clearValue) {
        this.clearValue = clearValue;
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