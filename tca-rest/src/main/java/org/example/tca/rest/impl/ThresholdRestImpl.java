package org.example.tca.rest.impl;

import org.example.tca.response.TCAResponseUtil;
import org.example.tca.rest.ThresholdRest;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.ThresholdVO;

import javax.ws.rs.core.Response;

public class ThresholdRestImpl implements ThresholdRest {

    private CentralizedService m_service;

    public ThresholdRestImpl(CentralizedService service) {
        m_service = service;
    }

    @Override
    public Response listThreshold(String name, String family) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getThresholds(name, family))
                    .build();
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Get all thresholds for " + TCAResponseUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response getThreshold(String name, String family, String objectType, String tcaLabel) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getThreshold(name, family, objectType, tcaLabel))
                    .build();
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Get threshold for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " failed", e);
        }
    }

    @Override
    public Response addThreshold(String name, String family, ThresholdVO thresholdVO) {
        try {
            m_service.addThreshold(name, family, thresholdVO);
            return TCAResponseUtil.printPassResponse("Added threshold for " + TCAResponseUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Add threshold for " + TCAResponseUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteThreshold(String name, String family, String objectType, String tcaLabel) {
        try {
            m_service.deleteThreshold(name, family, objectType, tcaLabel);
            return TCAResponseUtil.printPassResponse("Deleted threshold for " + TCAResponseUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete threshold for " + TCAResponseUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteAllThreshold(String name, String family) {
        try {
            m_service.deleteAllThreshold(name, family);
            return TCAResponseUtil.printPassResponse("Deleted all threshold for " + TCAResponseUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete all threshold for " + TCAResponseUtil.printPath(name, family) + " failed", e);
        }
    }
}
