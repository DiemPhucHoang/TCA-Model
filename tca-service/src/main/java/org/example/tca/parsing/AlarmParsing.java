package org.example.tca.parsing;

import org.example.tca.api.Alarm;
import org.example.tca.api.PerceivedSeverity;
import org.example.tca.exception.AlarmException;
import org.example.tca.exception.RuleException;
import org.example.tca.vo.AlarmVO;

public class AlarmParsing {
    public static Alarm parseAlarmVOToEntity(AlarmVO alarmVO) throws AlarmException {
        if (alarmVO == null) {
            throw new RuleException("Invalid alarm info");
        }

        PerceivedSeverity perceivedSeverity = null;
        if (alarmVO.getPerceivedSeverity() != null) {
            perceivedSeverity = PerceivedSeverity.from(alarmVO.getPerceivedSeverity());
            if (perceivedSeverity == null) {
                throw new AlarmException("Value " + alarmVO.getPerceivedSeverity() + " is invalid PerceivedSeverity value");
            }
        }

        return new Alarm(alarmVO.getAlarmTypeId(), perceivedSeverity);
    }
}
