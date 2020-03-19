package org.example.tca.api;

public enum ConditionLogicalOperator {
    and("and", 0),
    or("or", 1);

    private final String key;
    private final Integer value;

    ConditionLogicalOperator(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static ConditionLogicalOperator from(String key) {
        switch (key) {
            case "and":
                return ConditionLogicalOperator.and;
            case "or":
                return ConditionLogicalOperator.or;
            default:
                return null;
        }
    }

    public ConditionLogicalOperator from(Integer value) {
        switch (value) {
            case 0:
                return ConditionLogicalOperator.and;
            case 1:
                return ConditionLogicalOperator.or;
            default:
                return null;
        }
    }
}
