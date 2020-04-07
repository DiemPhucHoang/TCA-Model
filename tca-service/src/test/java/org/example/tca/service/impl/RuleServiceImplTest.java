package org.example.tca.service.impl;

import org.example.tca.api.Aggregator;
import org.example.tca.api.ConditionLogicalOperator;
import org.example.tca.api.Model;
import org.example.tca.api.Rule;
import org.example.tca.api.Threshold;
import org.example.tca.dao.ModelDAO;
import org.example.tca.dao.RuleDAO;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.RuleException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.parsing.RuleParsing;
import org.example.tca.vo.RuleVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.tca.service.impl.ModelServiceImplTest.MODEL_FAMILY_LS_DPU_CFAS_H;
import static org.example.tca.service.impl.ModelServiceImplTest.buildModel;
import static org.example.tca.service.impl.ThresholdServiceImplTest.THRESHOLD_OBJECT_TYPE_INTERFACE;
import static org.example.tca.service.impl.ThresholdServiceImplTest.THRESHOLD_TCA_LABEL;
import static org.example.tca.service.impl.ThresholdServiceImplTest.buildThreshold;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RuleServiceImplTest {
    private ModelDAO m_modelDAO;
    private ThresholdDAO m_thresholdDAO;
    private RuleDAO m_ruleDAO;
    private RuleServiceImpl m_ruleService;

    @Before
    public void init() {
        m_modelDAO = mock(ModelDAO.class);
        m_thresholdDAO = mock(ThresholdDAO.class);
        m_ruleDAO = mock(RuleDAO.class);
        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO);
    }

    @After
    public void destroy() {

    }

    @Test
    public void testGetRules_Empty() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL)).thenReturn(threshold);

        List<RuleVO> result = m_ruleService.getRules(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
        assertEquals(0, result.size());
    }

    @Test
    public void testGetRules_Has_1_Rule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL)).thenReturn(threshold);

        List<Rule> rules = new ArrayList<Rule>() {{
            add(buildRule(threshold));
        }};
        when(m_ruleDAO.list(threshold)).thenReturn(rules);
        List<RuleVO> result = m_ruleService.getRules(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetRules_Has_1_Multi_Rules() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, THRESHOLD_OBJECT_TYPE_INTERFACE, THRESHOLD_TCA_LABEL)).thenReturn(threshold);

        List<Rule> rules = new ArrayList<Rule>() {{
            add(buildRule(threshold));
            add(buildRule(threshold));
        }};

        when(m_ruleDAO.list(threshold)).thenReturn(rules);
        List<RuleVO> result = m_ruleService.getRules(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
        assertEquals(2, result.size());
    }

    @Test
    public void testGetRules_Exception_Model() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        try {
            m_ruleService.getRules(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testGetRules_Exception_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(null);

        try {
            m_ruleService.getRules(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals("Threshold with objectType '" + threshold.getObjectType() + "' and tcaLabel '" + threshold.getTcaLabel() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testGetRules_Exception_Rule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        doThrow(new RuleException("Get rules exception")).when(m_ruleDAO).list(threshold);

        try {
            m_ruleService.getRules(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof RuleException);
            assertEquals("Get rules exception", e.getMessage());
        }
    }

    @Test
    public void testGetRule_Not_Exists() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        when(m_ruleDAO.get(threshold, 1L)).thenReturn(null);

        RuleVO ruleVO = m_ruleService.getRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), 1L);
        assertNull(ruleVO);
    }

    @Test
    public void testGetRule_Exists() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        Rule rule = buildRule(threshold);

        when(m_ruleDAO.get(threshold, rule.getId())).thenReturn(rule);

        RuleVO ruleVO = m_ruleService.getRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), rule.getId());
        assertNotNull(ruleVO);
    }

    @Test
    public void testAddRule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        RuleVO ruleVO = new RuleVO(1L,"and", "avg", "PT6H");

        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);
        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        m_ruleService.addRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), ruleVO);
        verify(m_ruleDAO, times(1)).add(threshold, rule);
    }

    @Test
    public void testAddRule_Exception_Model() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        RuleVO ruleVO = new RuleVO(1L,"and", "avg", "PT6H");

        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);

        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        try {
            m_ruleService.addRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), ruleVO);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testAddRule_Exception_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(null);

        RuleVO ruleVO = new RuleVO(1L,"and", "avg", "PT6H");

        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);

        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        try {
            m_ruleService.addRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), ruleVO);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals("Threshold with objectType '" + threshold.getObjectType() + "' and tcaLabel '" + threshold.getTcaLabel() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testAddRule_Exception_Rule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        RuleVO ruleVO = new RuleVO(1l,"and", "avg", "PT6H");

        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);

        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        doThrow(new RuleException("Add rule exception")).when(m_ruleDAO).add(threshold, rule);

        try {
            m_ruleService.addRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), ruleVO);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof RuleException);
            assertEquals("Add rule exception", e.getMessage());
        }
    }

    @Test
    public void testUpdateRule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        RuleVO ruleVO = new RuleVO(1L,"and", "avg", "PT6H");
        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);

        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        m_ruleService.updateRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(),rule.getId(), ruleVO);
        verify(m_ruleDAO, times(1)).update(threshold, rule.getId(), rule);
    }

    @Test
    public void testUpdateRule_Exception_Model() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        RuleVO ruleVO = new RuleVO(1L,"and", "avg", "PT6H");
        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);

        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        try {
            m_ruleService.updateRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), rule.getId(), ruleVO);
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testUpdateRule_Exception_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(null);

        RuleVO ruleVO = new RuleVO(1L,"and", "avg", "PT6H");
        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);

        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        try {
            m_ruleService.updateRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), rule.getId(), ruleVO);
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals("Threshold with objectType '" + threshold.getObjectType() + "' and tcaLabel '" + threshold.getTcaLabel() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testUpdateRule_Exception_Rule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        RuleVO ruleVO = new RuleVO(1L,"and", "avg", "PT6H");
        Rule rule = RuleParsing.parseRuleVOToEntity(ruleVO);

        m_ruleService = new RuleServiceImpl(m_modelDAO, m_thresholdDAO, m_ruleDAO) {
            @Override
            protected Rule parseRuleVOToEntity(RuleVO ruleVO) {
                return rule;
            }
        };

        doThrow(new RuleException("Update rule exception")).when(m_ruleDAO).update(threshold,rule.getId(), rule);

        try {
            m_ruleService.updateRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), rule.getId(), ruleVO);
        } catch (Exception e) {
            assertTrue(e instanceof RuleException);
            assertEquals("Update rule exception", e.getMessage());
        }
    }

    @Test
    public void testDeleteRule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        m_ruleService.deleteRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), 1L);
        verify(m_ruleDAO, times(1)).delete(threshold, 1L);
    }

    @Test
    public void testDeleteRule_Exception_Model() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        try {
            m_ruleService.deleteRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), 1L);
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteRule_Exception_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(null);

        try {
            m_ruleService.deleteRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), 1L);
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals("Threshold with objectType '" + threshold.getObjectType() + "' and tcaLabel '" + threshold.getTcaLabel() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteRule_Exception_Rule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        doThrow(new RuleException("Delete rule exception")).when(m_ruleDAO).delete(threshold, 1L);

        try {
            m_ruleService.deleteRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel(), 1L);
        } catch (Exception e) {
            assertTrue(e instanceof RuleException);
            assertEquals("Delete rule exception", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllRule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        m_ruleService.deleteAllRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
        verify(m_ruleDAO).deleteAll(threshold);
    }

    @Test
    public void testDeleteAllRule_Exception_Model() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(null);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        try {
            m_ruleService.deleteAllRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
        } catch (Exception e) {
            assertTrue(e instanceof ModelException);
            assertEquals("Model with name '" + model.getName() + "' and family '" + model.getFamily() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllRule_Exception_Threshold() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(null);

        try {
            m_ruleService.deleteAllRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
        } catch (Exception e) {
            assertTrue(e instanceof ThresholdException);
            assertEquals("Threshold with objectType '" + threshold.getObjectType() + "' and tcaLabel '" + threshold.getTcaLabel() + "' does not exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteAllRule_Exception_Rule() {
        Model model = buildModel(MODEL_FAMILY_LS_DPU_CFAS_H);
        when(m_modelDAO.get(model.getName(), model.getFamily())).thenReturn(model);

        Threshold threshold = buildThreshold(model, THRESHOLD_OBJECT_TYPE_INTERFACE);
        when(m_thresholdDAO.get(model, threshold.getObjectType(), threshold.getTcaLabel())).thenReturn(threshold);

        doThrow(new RuleException("Delete all rule exception")).when(m_ruleDAO).deleteAll(threshold);

        try {
            m_ruleService.deleteAllRule(model.getName(), model.getFamily(), threshold.getObjectType(), threshold.getTcaLabel());
        } catch (Exception e) {
            assertTrue(e instanceof RuleException);
            assertEquals("Delete all rule exception", e.getMessage());
        }
    }

    public static Rule buildRule(Threshold threshold) {
        Rule rule = new Rule(1L, ConditionLogicalOperator.and, Aggregator.avg, "PT6H");
        rule.setThreshold(threshold);
        return rule;
    }
}
