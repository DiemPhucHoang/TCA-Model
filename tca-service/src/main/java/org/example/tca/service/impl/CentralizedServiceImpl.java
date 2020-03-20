package org.example.tca.service.impl;

import org.example.tca.exception.AlarmException;
import org.example.tca.exception.ConditionExeption;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.service.AlarmService;
import org.example.tca.service.CentralizedService;
import org.example.tca.service.ConditionService;
import org.example.tca.service.ModelService;
import org.example.tca.service.RuleService;
import org.example.tca.service.ThresholdService;
import org.example.tca.vo.AlarmVO;
import org.example.tca.vo.ConditionVO;
import org.example.tca.vo.ModelVO;
import org.example.tca.vo.RuleVO;
import org.example.tca.vo.ThresholdVO;

import java.util.List;

public class CentralizedServiceImpl implements CentralizedService {
    private ModelService m_modelService;
    private ThresholdService m_thresholdService;
    private RuleService m_ruleService;
    private ConditionService m_conditionService;
    private AlarmService m_alarmService;

    public CentralizedServiceImpl(ModelService modelService, ThresholdService thresholdService,
                                  RuleService ruleService, ConditionService conditionService,
                                  AlarmService alarmService) {
        this.m_modelService = modelService;
        this.m_thresholdService = thresholdService;
        this.m_ruleService = ruleService;
        this.m_conditionService = conditionService;
        this.m_alarmService = alarmService;
    }

    @Override
    public void importModel(String json, String fileName) throws Exception {
        m_modelService.importModel(json, fileName);
    }

    @Override
    public List<ModelVO> getModels() {
        return m_modelService.getModels();
    }

    @Override
    public ModelVO getModel(String name, String family) {
        return m_modelService.getModel(name, family);
    }

    @Override
    public void addModel(ModelVO modelVO) throws ModelException {
        m_modelService.addModel(modelVO);
    }

    @Override
    public void updateModel(String name, String family, ModelVO modelVO) throws ModelException {
        m_modelService.updateModel(name, family, modelVO);
    }

    @Override
    public void deleteModel(String name, String family) throws ModelException {
        m_modelService.deleteModel(name, family);
    }

    @Override
    public void deleteAllModel() throws ModelException {
        m_modelService.deleteAllModel();
    }

    // Threshold
    @Override
    public List<ThresholdVO> getThresholds(String name, String family) throws ModelException{
        return m_thresholdService.getThresholds(name, family);
    }

    @Override
    public ThresholdVO getThreshold(String name, String family, String objectType, String tcaLabel) throws ModelException {
        return m_thresholdService.getThreshold(name, family, objectType, tcaLabel);
    }

    @Override
    public void addThreshold(String name, String family, ThresholdVO thresholdVO)
            throws ModelException, ThresholdException {
        m_thresholdService.addThreshold(name, family, thresholdVO);
    }

    @Override
    public void deleteThreshold(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException {
        m_thresholdService.deleteThreshold(name, family, objectType, tcaLabel);
    }

    @Override
    public void deleteAllThreshold(String name, String family) throws ModelException {
        m_thresholdService.deleteAllThreshold(name, family);
    }

    @Override
    public List<RuleVO> getRules(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException {
        return m_ruleService.getRules(name, family, objectType, tcaLabel);
    }

    @Override
    public RuleVO getRule(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException {
        return m_ruleService.getRule(name, family, objectType, tcaLabel, id);
    }

    @Override
    public void addRule(String name, String family, String objectType, String tcaLabel, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.addRule(name, family, objectType, tcaLabel, ruleVO);
    }

    @Override
    public void updateRule(String name, String family, String objectType, String tcaLabel, Long id, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.updateRule(name, family, objectType, tcaLabel, id, ruleVO);
    }

    @Override
    public void deleteRule(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.deleteRule(name, family, objectType, tcaLabel, id);
    }

    @Override
    public void deleteAllRule(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.deleteAllRule(name, family, objectType, tcaLabel);
    }

    @Override
    public List<ConditionVO> getConditions(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException {
        return m_conditionService.getConditions(name, family, objectType, tcaLabel, id);
    }

    @Override
    public ConditionVO getCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName)
            throws ModelException, ThresholdException, RuleException {
        return m_conditionService.getCondition(name, family, objectType, tcaLabel, id, attributeName);
    }

    @Override
    public void addCondition(String name, String family, String objectType, String tcaLabel, Long id, ConditionVO conditionVO)
            throws ModelException, ThresholdException, RuleException, ConditionExeption {
        m_conditionService.addCondition(name, family, objectType, tcaLabel, id, conditionVO);
    }

    @Override
    public void updateCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName, ConditionVO conditionVO)
            throws ModelException, ThresholdException, RuleException, ConditionExeption {
        m_conditionService.updateCondition(name, family, objectType, tcaLabel, id, attributeName, conditionVO);
    }

    @Override
    public void deleteCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName)
            throws ModelException, ThresholdException, RuleException, ConditionExeption {
        m_conditionService.deleteCondition(name, family, objectType, tcaLabel, id, attributeName);
    }

    @Override
    public void deleteAllCondition(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException, ConditionExeption {
        m_conditionService.deleteAllCondition(name, family, objectType, tcaLabel, id);
    }

    @Override
    public AlarmVO getAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm) throws ModelException, ThresholdException, RuleException, AlarmException {
        return m_alarmService.getAlarm(name, family, objectType, tcaLabel, idRule, idAlarm);
    }

    @Override
    public void addAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, AlarmVO alarmVO) throws ModelException, ThresholdException, RuleException, AlarmException {
        m_alarmService.addAlarm(name, family, objectType, tcaLabel, idRule, alarmVO);
    }

    @Override
    public void updateAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm, AlarmVO alarmVO) throws ModelException, ThresholdException, RuleException, AlarmException {
        m_alarmService.updateAlarm(name, family, objectType, tcaLabel, idRule, idAlarm, alarmVO);
    }

    @Override
    public void deleteAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm) throws ModelException, ThresholdException, RuleException, AlarmException {
        m_alarmService.deleteAlarm(name, family, objectType, tcaLabel, idRule, idAlarm);
    }
}
