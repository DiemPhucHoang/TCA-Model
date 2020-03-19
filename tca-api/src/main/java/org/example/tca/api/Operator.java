package org.example.tca.api;

public enum Operator {
    eq("eq", 0),
    ne("ne", 1),
    lt("lt", 2),
    le("le", 3),
    gt("gt", 4),
    ge("ge", 5);

    private final String key;
    private final Integer value;

    Operator(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static Operator from(String key) {
        switch (key) {
            case "eq":
                return Operator.eq;
            case "ne":
                return Operator.ne;
            case "lt":
                return Operator.lt;
            case "le":
                return Operator.le;
            case "gt":
                return Operator.gt;
            case "ge":
                return Operator.ge;
            default:
                return null;
        }
    }

    public Operator from(Integer value) {
        switch (value) {
            case 0:
                return Operator.eq;
            case 1:
                return Operator.ne;
            case 2:
                return Operator.lt;
            case 3:
                return Operator.le;
            case 4:
                return Operator.gt;
            case 5:
                return Operator.ge;
            default:
                return null;
        }
    }
}
