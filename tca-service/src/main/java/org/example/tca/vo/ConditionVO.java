package org.example.tca.vo;

import org.example.tca.api.Condition;

public class ConditionVO {
    private String attributeName;
    private String attributeGuiName;
    private String objectType;
    private String operator;
    private Long value;
    private String clearOperator;
    private Long clearValue;
    private Boolean rate;

    public ConditionVO() {
    }

    public ConditionVO(Condition condition) {
        this.attributeName = condition.getAttributeName();
        this.attributeGuiName = condition.getAttributeGuiName();
        this.objectType = condition.getObjectType();
        this.operator = condition.getOperator().getKey();
        this.value = condition.getValue();
        this.clearOperator = condition.getClearOperator().getKey();
        this.clearValue = condition.getClearValue();
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getClearOperator() {
        return clearOperator;
    }

    public void setClearOperator(String clearOperator) {
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

    @Override
    public String toString() {
        return "ConditionVO{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeGuiName='" + attributeGuiName + '\'' +
                ", objectType='" + objectType + '\'' +
                ", operator=" + operator +
                ", value=" + value +
                ", clearOperator=" + clearOperator +
                ", clearValue=" + clearValue +
                ", rate=" + rate +
                '}';
    }
}
