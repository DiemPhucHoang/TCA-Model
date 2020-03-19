package org.example.tca.rest.impl;

import org.example.tca.rest.ConditionRest;
import org.example.tca.rest.TCARestUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.ConditionVO;

import javax.ws.rs.core.Response;

public class ConditionRestImpl implements ConditionRest {

    private CentralizedService m_service;

    public ConditionRestImpl(CentralizedService service) {
        m_service = service;
    }

    @Override
    public Response listCondition(String name, String family, String objectType, String tcaLable, Long id) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getConditions(name, family, objectType, tcaLable, id))
                    .build();
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Get all conditions for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id) + " failed", e);
        }
    }

    @Override
    public Response getCondition(String name, String family, String objectType, String tcaLable, Long id, String attributeName) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getCondition(name, family, objectType, tcaLable, id, attributeName))
                    .build();
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Get all condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id, attributeName) + " failed", e);
        }
    }

    @Override
    public Response addCondition(String name, String family, String objectType, String tcaLable, Long id, ConditionVO conditionVO) {
        try {
            m_service.addCondition(name, family, objectType, tcaLable, id, conditionVO);
            return TCARestUtil.printPassResponse("Added condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id, conditionVO.getAttributeName()) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Added condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id, conditionVO.getAttributeName()) + " failed", e);
        }
    }

    @Override
    public Response updateCondition(String name, String family, String objectType, String tcaLable, Long id, String attributeName, ConditionVO conditionVO) {
        try {
            m_service.updateCondition(name, family, objectType, tcaLable, id, attributeName, conditionVO);
            return TCARestUtil.printPassResponse("Updated condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id, conditionVO.getAttributeName()) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Updated condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id, conditionVO.getAttributeName()) + " failed", e);
        }
    }

    @Override
    public Response deleteCondition(String name, String family, String objectType, String tcaLable, Long id, String attributeName) {
        try {
            m_service.deleteCondition(name, family, objectType, tcaLable, id, attributeName);
            return TCARestUtil.printPassResponse("Updated condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id, attributeName) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Updated condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id, attributeName) + " failed", e);
        }
    }

    @Override
    public Response deleteAllCondition(String name, String family, String objectType, String tcaLable, Long id) {
        try {
            m_service.deleteAllCondition(name, family, objectType, tcaLable, id);
            return TCARestUtil.printPassResponse("Updated condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Updated condition for "
                    + TCARestUtil.printPath(name, family, objectType, tcaLable, id) + " failed", e);
        }
    }
}
