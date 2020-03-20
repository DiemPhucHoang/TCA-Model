package org.example.tca.api;

public enum Aggregator {

    avg("avg", 0),
    percentile_50th("percentile-50th", 1),
    percentile_75th("percentile-75th", 2),
    percentile_90th("percentile-90th", 3),
    percentile_99th("percentile-99th", 4),
    percentile_999th("percentile-999th", 5),
    min("min", 6),
    max("max", 7);

    private final String key;
    private final Integer value;

    Aggregator(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static Aggregator from(String key) {
        switch (key) {
            case "avg":
                return Aggregator.avg;
            case "percentile-50th":
                return Aggregator.percentile_50th;
            case "percentile-75th":
                return Aggregator.percentile_75th;
            case "percentile-90th":
                return Aggregator.percentile_90th;
            case "percentile-99th":
                return Aggregator.percentile_99th;
            case "percentile-999th":
                return Aggregator.percentile_999th;
            case "min":
                return Aggregator.min;
            case "max":
                return Aggregator.max;
            default:
                return null;
        }
    }
    public Aggregator from (Integer value) {
        switch (value) {
            case 0:
                return Aggregator.avg;
            case 1:
                return Aggregator.percentile_50th;
            case 2:
                return Aggregator.percentile_75th;
            case 3:
                return Aggregator.percentile_90th;
            case 4:
                return Aggregator.percentile_99th;
            case 5:
                return Aggregator.percentile_999th;
            case 6:
                return Aggregator.min;
            case 7:
                return Aggregator.max;
            default:
                return null;
        }
    }
}
