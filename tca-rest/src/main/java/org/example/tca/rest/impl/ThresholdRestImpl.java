package org.example.tca.rest.impl;

import org.example.tca.rest.TCARestUtil;
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
            return TCARestUtil.printFailResponse("Get all thresholds for " + TCARestUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response getThreshold(String name, String family, String objectType, String tcaLable) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getThreshold(name, family, objectType, tcaLable))
                    .build();
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Get all thresholds for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " failed", e);
        }
    }

    @Override
    public Response addThreshold(String name, String family, ThresholdVO thresholdVO) {
        try {
            m_service.addThreshold(name, family, thresholdVO);
            return TCARestUtil.printPassResponse("Added threshold for " + TCARestUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Added threshold for " + TCARestUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response updateThreshold(String name, String family, String objectType, String tcaLable, ThresholdVO thresholdVO) {
        try {
            m_service.updateThreshold(name, family, objectType, tcaLable, thresholdVO);
            return TCARestUtil.printPassResponse("Updated threshold for " + TCARestUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Updated threshold for " + TCARestUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteThreshold(String name, String family, String objectType, String tcaLable) {
        try {
            m_service.deleteThreshold(name, family, objectType, tcaLable);
            return TCARestUtil.printPassResponse("Deleted threshold for " + TCARestUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Deleted threshold for " + TCARestUtil.printPath(name, family) + " failed", e);
        }
    }

    @Override
    public Response deleteAllThreshold(String name, String family) {
        try {
            m_service.deleteAllThreshold(name, family);
            return TCARestUtil.printPassResponse("Delete all threshold for " + TCARestUtil.printPath(name, family) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Delete all threshold for " + TCARestUtil.printPath(name, family) + " failed", e);
        }
    }
}
