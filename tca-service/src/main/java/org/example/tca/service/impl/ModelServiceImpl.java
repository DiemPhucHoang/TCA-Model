package org.example.tca.service.impl;

import org.example.tca.api.Model;
import org.example.tca.dao.ModelDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.service.ModelService;
import org.example.tca.util.ModelUtil;
import org.example.tca.vo.ModelVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelServiceImpl implements ModelService {

    private ModelDAO m_modelDAO;

    public ModelServiceImpl(ModelDAO modelDAO) {
        this.m_modelDAO = modelDAO;
    }

    public List<ModelVO> getModels() {
        List<Model> models = m_modelDAO.list();
        if (models == null || models.isEmpty()) {
            return Collections.emptyList();
        }
        List<ModelVO> modelVOs = new ArrayList<>();
        for (Model model : models) {
            modelVOs.add(new ModelVO(model));
        }
        return modelVOs;
    }

    public ModelVO getModel(String name, String family) {
        Model model = m_modelDAO.get(name, family);
        return model == null ? null : new ModelVO(model);
    }

    public void addModel(ModelVO modelVO) throws ModelException {
        try {
            m_modelDAO.add(ModelUtil.parseModelVOToEntity(modelVO));
        } catch (Exception e) {
            System.out.println("Error while adding model. " + e.getMessage());
            throw new ModelException(e.getMessage());
        }
    }

    public void updateModel(String name, String family, ModelVO modelVO) throws ModelException {
        try {
            m_modelDAO.update(name, family, ModelUtil.parseModelVOToEntity(modelVO));
        } catch (Exception e) {
            System.out.println("Error while editing model. " + e.getMessage());
            throw new ModelException(e.getMessage());
        }

    }

    public void deleteModel(String name, String family) throws ModelException {
        try {
            m_modelDAO.delete(name, family);
        } catch (Exception e) {
            System.out.println("Error while deleting model. " + e);
            throw new ModelException(e.getMessage());
        }
    }

    @Override
    public void deleteAllModel() throws ModelException {
        try {
            m_modelDAO.deleteAll();
        } catch (Exception e) {
            throw new ModelException(e.getMessage());
        }
    }


}
