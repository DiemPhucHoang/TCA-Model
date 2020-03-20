package org.example.tca.response;

// Throw exactly exception layer, that we can navigate on GUI
public enum Level {

    Model("Model"),
    Threshold("Threshold"),
    Rule("Rule"),
    Condition("Condition"),
    Alarm("Alarm");

    private String m_status;

    Level(String status) {
        this.m_status = status;
    }

    public String getStatus() {
        return m_status;
    }

    public static Level from(String level) {
        switch (level) {
            case "ModelException" :
                return Level.Model;
            case "ThresholdException" :
                return Level.Threshold;
            case "RuleException" :
                return Level.Rule;
            case "ConditionException" :
                return Level.Condition;
            case "AlarmException" :
                return Level.Alarm;
            default:
                return null;
        }
    }
}
