package org.example.tca.service;

import org.example.tca.exception.AlarmException;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.vo.AlarmVO;

public interface AlarmService {
    AlarmVO getAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm)
            throws ModelException, ThresholdException, RuleException, AlarmException;

    void addAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, AlarmVO alarmVO)
            throws ModelException, ThresholdException, RuleException, AlarmException;

    void updateAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm, AlarmVO alarmVO)
            throws ModelException, ThresholdException, RuleException, AlarmException;

    void deleteAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm)
            throws ModelException, ThresholdException, RuleException, AlarmException;
}

