package org.example.tca.service.impl;

import org.example.tca.api.Model;
import org.example.tca.dao.ModelDAO;
import org.example.tca.dao.RuleDAO;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.service.RuleService;
import org.example.tca.vo.RuleVO;

import java.util.List;

public class RuleServiceImpl implements RuleService {
    private ModelDAO m_modelDAO;
    private ThresholdDAO m_thresholdDAO;
    private RuleDAO m_ruleDAO;

    @Override
    public List<RuleVO> getRules(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException {
        Model model = m_modelDAO.get(name, family);
        return null;
    }

    @Override
    public RuleVO getRule(String name, String family, String objectType, String tcaLabel, long id)
            throws ModelException, ThresholdException, RuleException {
        return null;
    }

    @Override
    public void addRule(String name, String family, String objectType, String tcaLabel, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {

    }

    @Override
    public void updateRule(String name, String family, String objectType, String tcaLabel, long id, RuleVO ruleVO)
            throws ModelException, ThresholdException, RuleException {

    }

    @Override
    public void deleteRule(String name, String family, String objectType, String tcaLabel, long id)
            throws ModelException, ThresholdException, RuleException {

    }

    @Override
    public void deleteAllRule(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException, RuleException {

    }
}
