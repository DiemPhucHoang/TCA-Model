package org.example.tca.service.impl;

import org.example.tca.api.Model;
import org.example.tca.api.Threshold;
import org.example.tca.dao.ModelDAO;
import org.example.tca.dao.ThresholdDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.exception.ThresholdException;
import org.example.tca.service.ThresholdService;
import org.example.tca.util.ThresholdUtil;
import org.example.tca.vo.ThresholdVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThresholdServiceImpl implements ThresholdService {

    private ModelDAO m_modelDAO;
    private ThresholdDAO m_thresholdDAO;

    public ThresholdServiceImpl(ModelDAO modelDAO, ThresholdDAO thresholdDAO) {
        this.m_modelDAO = modelDAO;
        this.m_thresholdDAO = thresholdDAO;
    }

    @Override
    public List<ThresholdVO> getThresholds(String name, String family) throws ModelException {

        Model modelDB = getModelDB(name, family);
        List<Threshold> thresholds = m_thresholdDAO.list(modelDB);
        if (thresholds == null || thresholds.isEmpty()) {
            return Collections.emptyList();
        }
        List<ThresholdVO> thresholdVOs = new ArrayList<>();
        for (Threshold threshold: thresholds) {
            thresholdVOs.add(new ThresholdVO(threshold));
        }

        return thresholdVOs;
    }

    @Override
    public ThresholdVO getThreshold(String name, String family, String objectType, String tcaLabel) throws ModelException {
        Model modelDB = getModelDB(name, family);
        Threshold threshold = m_thresholdDAO.get(modelDB, objectType, tcaLabel);
        return threshold == null ? null : new ThresholdVO(threshold);
    }

    @Override
    public void addThreshold(String name, String family, ThresholdVO thresholdVO)
            throws ModelException, ThresholdException {

        Model modelDB = getModelDB(name, family);

        try {
            m_thresholdDAO.add(modelDB, ThresholdUtil.parseThresholdVOToEntity(thresholdVO));

        } catch (Exception e) {
            throw new ThresholdException(e.getMessage());
        }
    }


    @Override
    public void updateThreshold(String name, String family, String objectType, String tcaLabel, ThresholdVO thresholdVO)
            throws ModelException, ThresholdException {
        Model modelDB = getModelDB(name, family);

        try {
            m_thresholdDAO.update(modelDB, objectType, tcaLabel, ThresholdUtil.parseThresholdVOToEntity(thresholdVO));
        } catch (Exception e) {
            throw new ThresholdException(e.getMessage());
        }
    }

    @Override
    public void deleteThreshold(String name, String family, String objectType, String tcaLabel)
            throws ModelException, ThresholdException {
        Model modelDB = getModelDB(name, family);
        try {
            m_thresholdDAO.delete(modelDB, objectType, tcaLabel);
        } catch (Exception e) {
            throw new ThresholdException(e.getMessage());
        }
    }

    @Override
    public void deleteAllThreshold(String name, String family) throws ModelException {
        Model modelDB = getModelDB(name, family);
        try {
            m_thresholdDAO.deleteAll(modelDB);
        } catch (Exception e) {
            throw new ThresholdException(e.getMessage());
        }
    }

    public Model getModelDB(String name, String family) throws ModelException {
        Model modelDB = m_modelDAO.get(name, family);
        if (modelDB == null) {
            throw new ModelException("Model with name '" + name + "' and family '" + family + "' does not exists");
        }
        return modelDB;
    }

}
