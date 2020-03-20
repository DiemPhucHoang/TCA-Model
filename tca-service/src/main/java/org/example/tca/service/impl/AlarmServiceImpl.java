package org.example.tca.service.impl;

import org.example.tca.api.Alarm;
import org.example.tca.api.Model;
import org.example.tca.api.Rule;
import org.example.tca.api.Threshold;
import org.example.tca.dao.AlarmDAO;
import org.example.tca.dao.ModelDAO;
import org.example.tca.dao.RuleDAO;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.AlarmException;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.service.AlarmService;
import org.example.tca.parsing.AlarmParsing;
import org.example.tca.vo.AlarmVO;

public class AlarmServiceImpl implements AlarmService {
    private ModelDAO m_modelDAO;
    private ThresholdDAO m_thresholdDAO;
    private RuleDAO m_ruleDAO;
    private AlarmDAO m_alarmDAO;

    public AlarmServiceImpl(ModelDAO modelDAO, ThresholdDAO thresholdDAO, RuleDAO ruleDAO, AlarmDAO alarmDAO) {
        this.m_modelDAO = modelDAO;
        this.m_thresholdDAO = thresholdDAO;
        this.m_ruleDAO = ruleDAO;
        this.m_alarmDAO = alarmDAO;
    }

    @Override
    public AlarmVO getAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm)
            throws ModelException, ThresholdException, RuleException, AlarmException {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, idRule);
        Alarm alarm = m_alarmDAO.get(ruleDB, idAlarm);
        return alarm == null ? null : new AlarmVO(alarm);
    }

    @Override
    public void addAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, AlarmVO alarmVO)
            throws ModelException, ThresholdException, RuleException, AlarmException {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, idRule);
        if (ruleDB.getAlarm() != null) {
            throw new RuleException("Rule with ID '" + idRule + "' has a alarm");
        }
        try {
            m_alarmDAO.add(ruleDB, AlarmParsing.parseAlarmVOToEntity(alarmVO));
        } catch(Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }

    @Override
    public void updateAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm, AlarmVO alarmVO)
            throws ModelException, ThresholdException, RuleException, AlarmException {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, idRule);
        try {
            m_alarmDAO.update(ruleDB, idAlarm, AlarmParsing.parseAlarmVOToEntity(alarmVO));
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }

    @Override
    public void deleteAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm)
            throws ModelException, ThresholdException, RuleException, AlarmException {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, idRule);
        try {
            m_alarmDAO.delete(ruleDB, idAlarm);
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }

    public Model getModelDB(String name, String family) {
        Model modelDB = m_modelDAO.get(name, family);
        if (modelDB == null) {
            throw new ModelException("Model with name '" + name + "' and family '" + family + "' does not exists");
        }
        return modelDB;
    }

    public Threshold getThresholdDB(String name, String family, String objectType, String tcaLabel) {
        Model modelDB = getModelDB(name, family);
        Threshold thresholdDB = m_thresholdDAO.get(modelDB, objectType, tcaLabel);
        if (thresholdDB == null) {
            throw new ThresholdException("Threshold with objectType '" + objectType + "' and tcaLabel '" + tcaLabel + "' does not exist");
        }
        return thresholdDB;
    }

    public Rule getRuleDB(String name, String family, String objectType, String tcaLabel, Long idRule) {
        Threshold thresholdDB = getThresholdDB(name, family, objectType, tcaLabel);
        Rule ruleDB = m_ruleDAO.get(thresholdDB, idRule);
        if (ruleDB == null) {
            throw new RuleException("Rule with ID '" + idRule + "' does not exist");
        }
        return ruleDB;
    }
}
