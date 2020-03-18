package org.example.tca.rest.impl;

import org.example.tca.rest.ModelRest;
import org.example.tca.rest.TCARestUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.ModelVO;

import javax.ws.rs.core.Response;
import java.util.Collection;

import static org.example.tca.rest.TCARestUtil.printFailResponse;
import static org.example.tca.rest.TCARestUtil.printPassResponse;


public class ModelRestImpl implements ModelRest {

    private CentralizedService m_service;

    public ModelRestImpl(CentralizedService service) {
        m_service = service;
    }

    @Override
    public Collection<ModelVO> listModels() {
        return m_service.getModels();
    }

    @Override
    public ModelVO getModel(String name, String family) {
        return m_service.getModel(name, family);
    }

    @Override
    public Response addModel(ModelVO modelVO) {
        try {
            m_service.addModel(modelVO);
            return TCARestUtil.printPassResponse("Added model for " + TCARestUtil.printPath(modelVO.getName(), modelVO.getFamily()) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Added model for " + TCARestUtil.printPath(modelVO.getName(), modelVO.getFamily()) + " failed", e);
        }
    }

    @Override
    public Response updateModel(String name, String family, ModelVO modelVO) {
        try {
            m_service.updateModel(name, family, modelVO);
            return TCARestUtil.printPassResponse("Updated model for " + TCARestUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Updated model for " + TCARestUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteModel(String name, String family) {
        try {
            m_service.deleteModel(name, family);
            return TCARestUtil.printPassResponse("Deleted model for " + TCARestUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Deleted model for " + TCARestUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteAllModel() {
        try {
            m_service.deleteAllModel();
            return TCARestUtil.printPassResponse("Delete all model successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Delete all model fail", e);
        }
    }
}
