package org.example.tca.service.impl;

import org.example.tca.api.Model;
import org.example.tca.dao.ModelDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.parsing.ModelParsing;
import org.example.tca.service.ModelService;
import org.example.tca.vo.ModelVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelServiceImpl implements ModelService {

    private ModelDAO m_modelDAO;

    public ModelServiceImpl(ModelDAO modelDAO) {
        this.m_modelDAO = modelDAO;
    }

    public void importModel(String json, String fileName) throws Exception {
        ModelVO modelVO = ModelParsing.parseJsonToObject(json);
        if (modelVO == null) {
            throw new ModelException("Error while parsing model VO file '" + fileName + "'");
        }

        String family = modelVO.getFamily();
        List<ModelVO> modelVOs = new ArrayList<>();
        // has multi family
        if (family.contains(",")) {
            String[] splitFamilies = family.split(",");
            for (String f : splitFamilies) {
                if (!f.trim().isEmpty()) {
                    ModelVO newModelVO = new ModelVO(modelVO);
                    newModelVO.setFamily(f.trim());
                    modelVOs.add(newModelVO);
                }
            }
        } else {    // has 1 family
            modelVOs.add(modelVO);
        }

        for (ModelVO m : modelVOs) {
            Model model = ModelParsing.convertVOToEntity(m);
            model.setModelFileName(fileName);
            m_modelDAO.add(model);
        }
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

    protected Model parseModelVOToEntity(ModelVO modelVO) throws Exception {
        return ModelParsing.parseModelVOToEntity(modelVO);
    }

    public void updateModel(String name, String family, ModelVO modelVO) throws ModelException {
        try {
            Model model = parseModelVOToEntity(modelVO);
            m_modelDAO.update(name, family, model);
        } catch (Exception e) {
            System.out.println("Error while editing model. " + e.getMessage());
            throw new ModelException(e.getMessage());
        }
    }

    public void deleteModel(String name, String family) throws ModelException {
        try {
            m_modelDAO.delete(name, family);
        } catch (Exception e) {
            System.out.println("Error while deleting model. " + e.getMessage());
            throw new ModelException(e.getMessage());
        }
    }

    public void deleteAllModel() throws ModelException {
        try {
            m_modelDAO.deleteAll();
        } catch (Exception e) {
            throw new ModelException(e.getMessage());
        }
    }
}
