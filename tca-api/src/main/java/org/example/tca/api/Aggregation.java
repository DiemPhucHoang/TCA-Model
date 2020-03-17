package org.example.tca.api;

public enum Aggregation {
    avg("avg", 0),
    percentile_50th("percentile_50th", 1),
    percentile_75th("percentile_75th", 2),
    percentile_90th("percentile_90th", 3),
    percentile_99th("percentile_99th", 4),
    percentile_999th("percentile_999th", 5),
    min("min", 6),
    max("max", 7);

    private final String key;
    private final Integer value;

    Aggregation(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public Aggregation from (String key) {
        switch (key) {
            case "avg":
                return Aggregation.avg;
            case "percentile_50th":
                return Aggregation.percentile_50th;
            case "percentile_75th":
                return Aggregation.percentile_75th;
            case "percentile_90th":
                return Aggregation.percentile_90th;
            case "percentile_99th":
                return Aggregation.percentile_99th;
            case "percentile_999th":
                return Aggregation.percentile_999th;
            case "min":
                return Aggregation.min;
            case "max":
                return Aggregation.max;
            default:
                return null;
        }
    }
    public Aggregation from (Integer value) {
        switch (value) {
            case 0:
                return Aggregation.avg;
            case 1:
                return Aggregation.percentile_50th;
            case 2:
                return Aggregation.percentile_75th;
            case 3:
                return Aggregation.percentile_90th;
            case 4:
                return Aggregation.percentile_99th;
            case 5:
                return Aggregation.percentile_999th;
            case 6:
                return Aggregation.min;
            case 7:
                return Aggregation.max;
            default:
                return null;
        }
    }
}
