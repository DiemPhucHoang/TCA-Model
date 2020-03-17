package org.example.tca.service.impl;

import org.example.tca.exception.ModelException;
import org.example.tca.service.CentralizedService;
import org.example.tca.service.ModelService;
import org.example.tca.service.ThresholdService;
import org.example.tca.vo.ModelVO;
import org.example.tca.vo.ThresholdVO;

import java.util.List;

    public class CentralizedServiceImpl implements CentralizedService {
    private ModelService m_modelService;
    private ThresholdService m_thresholdService;

    public CentralizedServiceImpl(ModelService modelService, ThresholdService thresholdService) {
        m_modelService = modelService;
        m_thresholdService = thresholdService;
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
    public void addModel(ModelVO modelVO) {
        m_modelService.addModel(modelVO);
    }

    @Override
    public void updateModel(String name, String family, ModelVO modelVO) {
        m_modelService.updateModel(name, family, modelVO);
    }

    @Override
    public void deleteModel(String name, String family) {
        m_modelService.deleteModel(name, family);
    }

    @Override
    public void deleteAllModel() throws ModelException {
        m_modelService.deleteAllModel();
    }

    // Threshold
    @Override
    public List<ThresholdVO> getThresholds(String name, String family) {
        return m_thresholdService.getThresholds(name, family);
    }

    @Override
    public ThresholdVO getThreshold(String name, String family, String objectType, String tcaLabel) {
        return m_thresholdService.getThreshold(name, family, objectType, tcaLabel);
    }

    @Override
    public void addThreshold(String name, String family, ThresholdVO thresholdVO) {
        m_thresholdService.addThreshold(name, family, thresholdVO);
    }

    @Override
    public void updateThreshold(String name, String family, String objectType, String tcaLabel, ThresholdVO thresholdVO) {
        m_thresholdService.updateThreshold(name, family, objectType, tcaLabel, thresholdVO);
    }

    @Override
    public void deleteThreshold(String name, String family, String objectType, String tcaLabel) {
        m_thresholdService.deleteThreshold(name, family, objectType, tcaLabel);
    }

    @Override
    public void deleteAllThreshold(String name, String family) throws ModelException {
        m_thresholdService.deleteAllThreshold(name, family);
    }
}
