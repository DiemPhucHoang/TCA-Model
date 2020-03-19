package org.example.tca.vo;

import org.example.tca.api.Alarm;

public class AlarmVO {

    private String alarmTypeId;
    private String perceivedSeverity;

    public AlarmVO() {
    }

    public AlarmVO(Alarm alarm) {
        this.alarmTypeId = alarm.getAlarmTypeId();
        this.perceivedSeverity = alarm.getPerceivedSeverity().getKey();
    }

    public String getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(String alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public String getPerceivedSeverity() {
        return perceivedSeverity;
    }

    public void setPerceivedSeverity(String perceivedSeverity) {
        this.perceivedSeverity = perceivedSeverity;
    }
}
