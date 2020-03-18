package org.example.tca.rest.impl;

import org.example.tca.rest.CentralizedRest;
import org.example.tca.rest.ModelRest;
import org.example.tca.rest.RuleRest;
import org.example.tca.rest.ThresholdRest;
import org.example.tca.vo.ModelVO;
import org.example.tca.vo.RuleVO;
import org.example.tca.vo.ThresholdVO;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class CentralizedRestImpl implements CentralizedRest {

    private ModelRest m_modelRest;
    private ThresholdRest m_thresholdRest;
    private RuleRest m_ruleRest;

    public CentralizedRestImpl(ModelRest tcaRest, ThresholdRest thresholdRest, RuleRest ruleRest) {
        this.m_modelRest = tcaRest;
        this.m_thresholdRest = thresholdRest;
        this.m_ruleRest = ruleRest;
    }

    @Override
    public Collection<ModelVO> listModels() {
        return m_modelRest.listModels();
    }

    @Override
    public ModelVO getModel(String name, String family) {
        return m_modelRest.getModel(name, family);
    }

    @Override
    public Response addModel(ModelVO modelVO) {
        return m_modelRest.addModel(modelVO);
    }

    @Override
    public Response updateModel(String name, String family, ModelVO modelVO) {
        return m_modelRest.updateModel(name, family, modelVO);
    }

    @Override
    public Response deleteModel(String name, String family) {
        return m_modelRest.deleteModel(name, family);
    }

    @Override
    public Response deleteAllModel() {
        return m_modelRest.deleteAllModel();
    }

    @Override
    public Response listThreshold(String name, String family) {
        return m_thresholdRest.listThreshold(name, family);
    }

    @Override
    public Response getThreshold(String name, String family, String objectType, String tcaLable) {
        return m_thresholdRest.getThreshold(name, family, objectType, tcaLable);
    }

    @Override
    public Response addThreshold(String name, String family, ThresholdVO thresholdVO) {
        return m_thresholdRest.addThreshold(name, family, thresholdVO);
    }

    @Override
    public Response updateThreshold(String name, String family, String objectType, String tcaLable, ThresholdVO thresholdVO) {
        return m_thresholdRest.updateThreshold(name, family, objectType, tcaLable, thresholdVO);
    }

    @Override
    public Response deleteThreshold(String name, String family, String objectType, String tcaLable) {
        return m_thresholdRest.deleteThreshold(name, family, objectType, tcaLable);
    }

    @Override
    public Response deleteAllThreshold(String name, String family) {
        return m_thresholdRest.deleteAllThreshold(name, family);
    }

    @Override
    public Response listRule(String name, String family, String objectType, String tcaLable) {
        return m_ruleRest.listRule(name, family, objectType, tcaLable);
    }

    @Override
    public Response getRule(String name, String family, String objectType, String tcaLable, Long id) {
        return m_ruleRest.getRule(name, family, objectType, tcaLable, id);
    }

    @Override
    public Response addRule(String name, String family, String objectType, String tcaLable, RuleVO ruleVO) {
        return m_ruleRest.addRule(name, family, objectType, tcaLable, ruleVO);
    }

    @Override
    public Response updateRule(String name, String family, String objectType, String tcaLable, Long id, RuleVO ruleVO) {
        return m_ruleRest.updateRule(name, family, objectType, tcaLable, id, ruleVO);
    }

    @Override
    public Response deleteRule(String name, String family, String objectType, String tcaLable, Long id) {
        return m_ruleRest.deleteRule(name, family, objectType, tcaLable, id);
    }

    @Override
    public Response deleteAllRule(String name, String family, String objectType, String tcaLable) {
        return m_ruleRest.deleteAllRule(name, family, objectType, tcaLable);
    }
}
