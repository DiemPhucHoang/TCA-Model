package org.example.tca.api;

public enum PerceivedSeverity {
    indeterminate("indeterminate", 0),
    minor("minor", 1),
    warning("warning", 2),
    major("major", 3),
    critical("critical", 4);

    private final String key;
    private final Integer value;

    PerceivedSeverity(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static PerceivedSeverity from(String key) {
        switch (key) {
            case "indeterminate":
                return PerceivedSeverity.indeterminate;
            case "minor":
                return PerceivedSeverity.minor;
            case "warning":
                return PerceivedSeverity.warning;
            case "major":
                return PerceivedSeverity.major;
            case "critical":
                return PerceivedSeverity.critical;
            default:
                return null;
        }
    }

    public PerceivedSeverity from(Integer value) {
        switch (value) {
            case 0:
                return PerceivedSeverity.indeterminate;
            case 1:
                return PerceivedSeverity.minor;
            case 2:
                return PerceivedSeverity.warning;
            case 3:
                return PerceivedSeverity.major;
            case 4:
                return PerceivedSeverity.critical;
            default:
                return null;
        }
    }
}
