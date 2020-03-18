package org.example.tca.service.impl;

import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.service.CentralizedService;
import org.example.tca.service.ModelService;
import org.example.tca.service.RuleService;
import org.example.tca.service.ThresholdService;
import org.example.tca.vo.ModelVO;
import org.example.tca.vo.RuleVO;
import org.example.tca.vo.ThresholdVO;

import java.util.List;

public class CentralizedServiceImpl implements CentralizedService {
    private ModelService m_modelService;
    private ThresholdService m_thresholdService;
    private RuleService m_ruleService;

    public CentralizedServiceImpl(ModelService modelService, ThresholdService thresholdService, RuleService ruleService) {
        this.m_modelService = modelService;
        this.m_thresholdService = thresholdService;
        this.m_ruleService = ruleService;
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
    public void updateThreshold(String name, String family, String objectType, String tcaLabel, ThresholdVO thresholdVO)
            throws ModelException, ThresholdException {
        m_thresholdService.updateThreshold(name, family, objectType, tcaLabel, thresholdVO);
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
    public RuleVO getRule(String name, String family, String objectType, String tcaLabel, long id)
            throws ModelException, ThresholdException, RuleException {
        return m_ruleService.getRule(name, family, objectType, tcaLabel, id);
    }

    @Override
    public void addRule(String name, String family, String objectType, String tcaLabel, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.addRule(name, family, objectType, tcaLabel, ruleVO);
    }

    @Override
    public void updateRule(String name, String family, String objectType, String tcaLabel, long id, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.updateRule(name, family, objectType, tcaLabel, id, ruleVO);
    }

    @Override
    public void deleteRule(String name, String family, String objectType, String tcaLabel, long id)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.deleteRule(name, family, objectType, tcaLabel, id);
    }

    @Override
    public void deleteAllRule(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException {
        m_ruleService.deleteAllRule(name, family, objectType, tcaLabel);
    }
}
