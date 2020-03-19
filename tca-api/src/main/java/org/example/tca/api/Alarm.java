package org.example.tca.api;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity (name = "Alarm")
@Table (name = "tca_alarm")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "alarm_type_id", nullable = false, length = 128)
    private String alarmTypeId;

    @Column(name = "perceived_severity", nullable = false)
    private PerceivedSeverity perceivedSeverity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", nullable = false)
    private Rule rule;

    public Alarm() {
    }

    public Alarm(String alarmTypeId, PerceivedSeverity perceivedSeverity) {
        this.alarmTypeId = alarmTypeId;
        this.perceivedSeverity = perceivedSeverity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmTypeId() {
        return alarmTypeId;
    }

    public void setAlarmTypeId(String alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    public PerceivedSeverity getPerceivedSeverity() {
        return perceivedSeverity;
    }

    public void setPerceivedSeverity(PerceivedSeverity perceivedSeverity) {
        this.perceivedSeverity = perceivedSeverity;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
