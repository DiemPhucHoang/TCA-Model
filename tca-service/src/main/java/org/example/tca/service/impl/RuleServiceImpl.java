package org.example.tca.service.impl;

import org.example.tca.api.Model;
import org.example.tca.api.Rule;
import org.example.tca.api.Threshold;
import org.example.tca.dao.ModelDAO;
import org.example.tca.dao.RuleDAO;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.service.RuleService;
import org.example.tca.util.RuleUtil;
import org.example.tca.vo.RuleVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RuleServiceImpl implements RuleService {
    private ModelDAO m_modelDAO;
    private ThresholdDAO m_thresholdDAO;
    private RuleDAO m_ruleDAO;


    public RuleServiceImpl(ModelDAO modelDAO, ThresholdDAO thresholdDAO, RuleDAO ruleDAO) {
        m_modelDAO = modelDAO;
        m_thresholdDAO = thresholdDAO;
        m_ruleDAO = ruleDAO;
    }

    @Override
    public List<RuleVO> getRules(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException {
        Threshold thresholdDB = getThresholdDB(name, family, objectType, tcaLabel);
        List<Rule> rules = m_ruleDAO.list(thresholdDB);
        if (rules == null || rules.isEmpty()) {
            return Collections.emptyList();
        }
        List<RuleVO> ruleVOs = new ArrayList<>();
        for (Rule rule: rules) {
            ruleVOs.add(new RuleVO(rule));
        }
        return ruleVOs;
    }

    @Override
    public RuleVO getRule(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException {
        Threshold thresholdDB = getThresholdDB(name, family, objectType, tcaLabel);
        Rule rule = m_ruleDAO.get(thresholdDB, id);
        return rule == null ? null : new RuleVO(rule);
    }

    @Override
    public void addRule(String name, String family, String objectType, String tcaLabel, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {
        Threshold thresholdDB = getThresholdDB(name, family, objectType, tcaLabel);
        try {
            m_ruleDAO.add(thresholdDB, RuleUtil.parseRuleVOToEntity(ruleVO));
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
        }

    }

    @Override
    public void updateRule(String name, String family, String objectType, String tcaLabel, Long id, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {
        Threshold thresholdDB = getThresholdDB(name, family, objectType, tcaLabel);
        try {
            m_ruleDAO.update(thresholdDB, id, RuleUtil.parseRuleVOToEntity(ruleVO));
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
        }
    }

    @Override
    public void deleteRule(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException {
        Threshold thresholdDB = getThresholdDB(name, family, objectType, tcaLabel);
        try {
            m_ruleDAO.delete(thresholdDB, id);
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
        }
    }

    @Override
    public void deleteAllRule(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException {
        Threshold thresholdDB = getThresholdDB(name, family, objectType, tcaLabel);
        try {
            m_ruleDAO.deleteAll(thresholdDB);
        } catch (Exception e) {
            throw new RuleException(e.getMessage());
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
            throw new ThresholdException("Threshold with objectType '" + objectType + "' and tcaLabel '" + tcaLabel + "' does not exists");
        }
        return thresholdDB;
    }
}
