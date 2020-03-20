package org.example.tca.rest.impl;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.example.tca.rest.ModelRest;
import org.example.tca.response.TCAResponseUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.ModelVO;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

public class ModelRestImpl implements ModelRest {

    private CentralizedService m_service;

    public ModelRestImpl(CentralizedService service) {
        m_service = service;
    }

    @Override
    public Response importModel(Attachment attachment) {
        try {
            InputStream inputStream = attachment.getDataHandler().getInputStream();
            String fileName = attachment.getDataHandler().getName();
            String jsonString = new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .parallel().collect(Collectors.joining("\n"));
            m_service.importModel(jsonString, fileName);
            return TCAResponseUtil.printPassResponse("Imported model successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Import model failed", e);
        }
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
    public Response updateModel(String name, String family, ModelVO modelVO) {
        try {
            m_service.updateModel(name, family, modelVO);
            return TCAResponseUtil.printPassResponse("Updated model for " + TCAResponseUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Update model for " + TCAResponseUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteModel(String name, String family) {
        try {
            m_service.deleteModel(name, family);
            return TCAResponseUtil.printPassResponse("Deleted model for " + TCAResponseUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete model for " + TCAResponseUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteAllModel() {
        try {
            m_service.deleteAllModel();
            return TCAResponseUtil.printPassResponse("Deleted all model successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete all model fail", e);
        }
    }
}
