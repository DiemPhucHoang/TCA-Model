package org.example.tca.rest.impl;

import org.example.tca.rest.CentralizedRest;
import org.example.tca.rest.ModelRest;
import org.example.tca.rest.ThresholdRest;
import org.example.tca.vo.ModelVO;
import org.example.tca.vo.ThresholdVO;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class CentralizedRestImpl implements CentralizedRest {

    private ModelRest m_modelRest;
    private ThresholdRest m_thresholdRest;

    public CentralizedRestImpl(ModelRest tcaRest, ThresholdRest thresholdRest) {
        m_modelRest = tcaRest;
        m_thresholdRest = thresholdRest;
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

}
