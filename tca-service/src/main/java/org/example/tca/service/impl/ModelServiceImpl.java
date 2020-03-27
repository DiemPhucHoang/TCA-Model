package org.example.tca.service.impl;

import org.example.tca.api.Model;
import org.example.tca.dao.ModelDAO;
import org.example.tca.exception.ModelException;
import org.example.tca.service.ModelService;
import org.example.tca.parsing.ModelParsing;
import org.example.tca.vo.ModelVO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelServiceImpl implements ModelService {

    private ModelDAO m_modelDAO;

    private static final String MODEL_BACKUP_DIR = "/home/nminhtuan/temp_karaf";

    public ModelServiceImpl(ModelDAO modelDAO) {
        this.m_modelDAO = modelDAO;
    }

    @Override
    public void importModel(String json, String fileName) throws Exception {
        ModelVO modelVO = ModelParsing.parseJsonToObject(json);
        if (modelVO == null) {
            throw new ModelException("Error while parsing model vo file '" + fileName + "'");
        }

        String family = modelVO.getFamily();
        List<ModelVO> modelVOs = new ArrayList<>();
        if (family.contains(",")) {
            String[] splitFamilies = family.split(",");
            for (String f : splitFamilies) {
                if (!f.trim().isEmpty()) {
                    ModelVO newModelVO = new ModelVO(modelVO);
                    newModelVO.setFamily(f.trim());
                    modelVOs.add(newModelVO);
                }
            }
        } else {
            modelVOs.add(modelVO);
        }

        for (ModelVO m : modelVOs) {
            Model model = ModelParsing.convertVOToEntity(m);
            model.setModelFileName(fileName);
            m_modelDAO.add(model);
        }

//        String filePath = MODEL_BACKUP_DIR + "/" + fileName;
//        saveToFile(json, filePath, MODEL_BACKUP_DIR);
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
            m_modelDAO.add(ModelParsing.parseModelVOToEntity(modelVO));
        } catch (Exception e) {
            System.out.println("Error while adding model. " + e.getMessage());
            throw new ModelException(e.getMessage());
        }
    }

    public void updateModel(String name, String family, ModelVO modelVO) throws ModelException {
        try {
            m_modelDAO.update(name, family, ModelParsing.parseModelVOToEntity(modelVO));
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

    private static void saveToFile(String json, String filePath, String dirPath) throws IOException {
        File folder = new File(dirPath);
        if (folder.exists()) {
            deleteDir(folder);
        }
        folder.mkdirs();
        try (PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
            out.print(json);
        }
    }

    private static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }
}
