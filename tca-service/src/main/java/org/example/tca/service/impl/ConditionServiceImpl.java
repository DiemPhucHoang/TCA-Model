package org.example.tca.service.impl;

import org.example.tca.api.Condition;
import org.example.tca.api.Model;
import org.example.tca.api.Rule;
import org.example.tca.api.Threshold;
import org.example.tca.dao.ConditionDAO;
import org.example.tca.dao.ModelDAO;
import org.example.tca.dao.RuleDAO;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.ConditionExeption;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.service.ConditionService;
import org.example.tca.util.ConditionUtil;
import org.example.tca.vo.ConditionVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConditionServiceImpl implements ConditionService {

    private ModelDAO m_modelDAO;
    private ThresholdDAO m_thresholdDAO;
    private RuleDAO m_ruleDAO;
    private ConditionDAO m_conditionDAO;

    public ConditionServiceImpl(ModelDAO modelDAO, ThresholdDAO thresholdDAO, RuleDAO ruleDAO, ConditionDAO conditionDAO) {
        this.m_modelDAO = modelDAO;
        this.m_thresholdDAO = thresholdDAO;
        this.m_ruleDAO = ruleDAO;
        this.m_conditionDAO = conditionDAO;
    }

    @Override
    public List<ConditionVO> getConditions(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, id);
        List<Condition> conditions = m_conditionDAO.list(ruleDB);
        if (conditions == null || conditions.isEmpty()) {
            return Collections.emptyList();
        }
        List<ConditionVO> conditionVOs = new ArrayList<>();
        for (Condition condition: conditions) {
            conditionVOs.add(new ConditionVO(condition));
        }
        return conditionVOs;
    }



    @Override
    public ConditionVO getCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName)
            throws ModelException, ThresholdException, RuleException {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, id);
        Condition condition = m_conditionDAO.get(ruleDB, attributeName);
        return condition == null ? null : new ConditionVO(condition);
    }

    @Override
    public void addCondition(String name, String family, String objectType, String tcaLabel, Long id, ConditionVO conditionVO) throws ModelException, ThresholdException, RuleException, ConditionExeption {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, id);

        try {
            m_conditionDAO.add(ruleDB, ConditionUtil.parseConditionVOToEntity(conditionVO));
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
        }
    }

    @Override
    public void updateCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName, ConditionVO conditionVO) throws ModelException, ThresholdException, RuleException, ConditionExeption {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, id);
        try {
            m_conditionDAO.update(ruleDB, attributeName, ConditionUtil.parseConditionVOToEntity(conditionVO));
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
        }
    }

    @Override
    public void deleteCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName) throws ModelException, ThresholdException, RuleException, ConditionExeption {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, id);
        try {
            m_conditionDAO.delete(ruleDB, attributeName);
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
        }
    }

    @Override
    public void deleteAllCondition(String name, String family, String objectType, String tcaLabel, Long id) throws ModelException, ThresholdException, RuleException, ConditionExeption {
        Rule ruleDB = getRuleDB(name, family, objectType, tcaLabel, id);
        try {
            m_conditionDAO.deleteAll(ruleDB);
        } catch (Exception e) {
            throw new ConditionExeption(e.getMessage());
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

    public Rule getRuleDB(String name, String family, String objectType, String tcaLabel, Long id) {
        Model modelDB = getModelDB(name, family);
        Threshold thresholdDB = getThresholdDB(modelDB.getName(), modelDB.getFamily(), objectType, tcaLabel);
        Rule ruleDB = m_ruleDAO.get(thresholdDB, id);
        if (ruleDB == null) {
            throw new RuleException("Rule with ID '" + id + "' does not exists");
        }
        return ruleDB;
    }
}
