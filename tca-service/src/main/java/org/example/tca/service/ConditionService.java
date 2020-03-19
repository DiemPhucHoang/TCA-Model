package org.example.tca.service;

import org.example.tca.exception.ConditionExeption;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.vo.ConditionVO;

import java.util.List;

public interface ConditionService {

    List<ConditionVO> getConditions(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException;

    ConditionVO getCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName)
            throws ModelException, ThresholdException, RuleException;

    void addCondition(String name, String family, String objectType, String tcaLabel, Long id, ConditionVO conditionVO)
            throws ModelException, ThresholdException, RuleException, ConditionExeption;

    void updateCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName, ConditionVO conditionVO)
            throws ModelException, ThresholdException, RuleException, ConditionExeption;

    void deleteCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName)
            throws ModelException, ThresholdException, RuleException, ConditionExeption;

    void deleteAllCondition(String name, String family, String objectType, String tcaLabel, Long id)
            throws ModelException, ThresholdException, RuleException, ConditionExeption;
}
