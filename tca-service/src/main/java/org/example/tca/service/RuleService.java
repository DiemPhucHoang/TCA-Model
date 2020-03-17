package org.example.tca.service;

import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.vo.RuleVO;

import java.util.List;

public interface RuleService {
    List<RuleVO> getRules(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException;

    RuleVO getRule(String name, String family, String objectType, String tcaLabel, long id)
            throws ModelException, ThresholdException, RuleException;

    void addRule(String name, String family, String objectType, String tcaLabel, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException;

    void updateRule(String name, String family, String objectType, String tcaLabel, long id, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException;

    void deleteRule(String name, String family, String objectType, String tcaLabel, long id)
            throws ModelException, ThresholdException, RuleException;

    void deleteAllRule(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException;
}
