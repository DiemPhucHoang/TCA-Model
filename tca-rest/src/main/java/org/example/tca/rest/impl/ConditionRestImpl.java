package org.example.tca.rest.impl;

import org.example.tca.rest.ConditionRest;
import org.example.tca.response.TCAResponseUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.ConditionVO;

import javax.ws.rs.core.Response;

public class ConditionRestImpl implements ConditionRest {

    private CentralizedService m_service;

    public ConditionRestImpl(CentralizedService service) {
        m_service = service;
    }

    @Override
    public Response listCondition(String name, String family, String objectType, String tcaLabel, Long id) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getConditions(name, family, objectType, tcaLabel, id))
                    .build();
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Get all conditions for "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id) + " failed", e);
        }
    }

    @Override
    public Response getCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getCondition(name, family, objectType, tcaLabel, id, attributeName))
                    .build();
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Get all condition for "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id, attributeName) + " failed", e);
        }
    }

    @Override
    public Response addCondition(String name, String family, String objectType, String tcaLabel, Long id, ConditionVO conditionVO) {
        try {
            m_service.addCondition(name, family, objectType, tcaLabel, id, conditionVO);
            return TCAResponseUtil.printPassResponse("Added condition "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id, conditionVO.getAttributeName()) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Add condition "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id, conditionVO.getAttributeName()) + " failed", e);
        }
    }

    @Override
    public Response updateCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName, ConditionVO conditionVO) {
        try {
            m_service.updateCondition(name, family, objectType, tcaLabel, id, attributeName, conditionVO);
            return TCAResponseUtil.printPassResponse("Updated condition "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id, conditionVO.getAttributeName()) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Update condition "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id, conditionVO.getAttributeName()) + " failed", e);
        }
    }

    @Override
    public Response deleteCondition(String name, String family, String objectType, String tcaLabel, Long id, String attributeName) {
        try {
            m_service.deleteCondition(name, family, objectType, tcaLabel, id, attributeName);
            return TCAResponseUtil.printPassResponse("Deleted condition "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id, attributeName) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete condition "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id, attributeName) + " failed", e);
        }
    }

    @Override
    public Response deleteAllCondition(String name, String family, String objectType, String tcaLabel, Long id) {
        try {
            m_service.deleteAllCondition(name, family, objectType, tcaLabel, id);
            return TCAResponseUtil.printPassResponse("Deleted all conditions for "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete all conditions for "
                    + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, id) + " failed", e);
        }
    }
}
