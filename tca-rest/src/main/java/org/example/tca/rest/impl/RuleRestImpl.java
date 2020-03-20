package org.example.tca.rest.impl;

import org.example.tca.rest.RuleRest;
import org.example.tca.response.TCAResponseUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.RuleVO;

import javax.ws.rs.core.Response;

public class RuleRestImpl implements RuleRest {

    private CentralizedService m_service;

    public RuleRestImpl(CentralizedService m_service) {
        this.m_service = m_service;
    }

    @Override
    public Response listRule(String name, String family, String objectType, String tcaLabel) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getRules(name, family, objectType, tcaLabel))
                    .build();
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Get all rules for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " failed", e);
        }
    }

    @Override
    public Response getRule(String name, String family, String objectType, String tcaLabel, Long id) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getRule(name, family, objectType, tcaLabel, id))
                    .build();
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Get rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " failed", e);
        }
    }

    @Override
    public Response addRule(String name, String family, String objectType, String tcaLabel, RuleVO ruleVO) {
        try {
            m_service.addRule(name, family, objectType, tcaLabel, ruleVO);
            return TCAResponseUtil.printPassResponse("Added rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Add rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " failed", e);
        }
    }

    @Override
    public Response updateRule(String name, String family, String objectType, String tcaLabel, Long id, RuleVO ruleVO) {
        try {
            m_service.updateRule(name, family, objectType, tcaLabel, id, ruleVO);
            return TCAResponseUtil.printPassResponse("Updated rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Update rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " failed", e);
        }
    }

    @Override
    public Response deleteRule(String name, String family, String objectType, String tcaLabel, Long id) {
        try {
            m_service.deleteRule(name, family, objectType, tcaLabel, id);
            return TCAResponseUtil.printPassResponse("Deleted rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " failed", e);
        }
    }

    @Override
    public Response deleteAllRule(String name, String family, String objectType, String tcaLabel) {
        try {
            m_service.deleteAllRule(name, family, objectType, tcaLabel);
            return TCAResponseUtil.printPassResponse("Deleted all rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Delete all rule for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel) + " failed", e);
        }
    }
}
