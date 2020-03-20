package org.example.tca.vo;

import org.example.tca.api.Condition;

public class ConditionVO {

    private String attributeName;
    private String attributeGuiName;
    private String objectType;
    private String operator;
    private Long defaultValue;
    private String clearOperator;
    private Long clearDefaultValue;
    private Boolean rate;
    private Double counterMax;

    public ConditionVO() {
    }

    public ConditionVO(Condition condition) {
        this.attributeName = condition.getAttributeName();
        this.attributeGuiName = condition.getAttributeGuiName();
        this.objectType = condition.getObjectType();
        this.operator = condition.getOperator().getKey();
        this.defaultValue = condition.getValue();
        this.clearOperator = condition.getClearOperator().getKey();
        this.clearDefaultValue = condition.getClearValue();
        this.rate = condition.getRate();
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Boolean getRate() {
        return rate;
    }

    public void setRate(Boolean rate) {
        this.rate = rate;
    }

    public Long getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Long defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getClearOperator() {
        return clearOperator;
    }

    public void setClearOperator(String clearOperator) {
        this.clearOperator = clearOperator;
    }

    public Long getClearDefaultValue() {
        return clearDefaultValue;
    }

    public void setClearDefaultValue(Long clearDefaultValue) {
        this.clearDefaultValue = clearDefaultValue;
    }

    public Double getCounterMax() {
        return counterMax;
    }

    public void setCounterMax(Double counterMax) {
        this.counterMax = counterMax;
    }

    @Override
    public String toString() {
        return "ConditionVO{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeGuiName='" + attributeGuiName + '\'' +
                ", objectType='" + objectType + '\'' +
                ", operator='" + operator + '\'' +
                ", defaultValue=" + defaultValue +
                ", clearOperator='" + clearOperator + '\'' +
                ", clearDefaultValue=" + clearDefaultValue +
                ", rate=" + rate +
                ", counterMax=" + counterMax +
                '}';
    }
}
