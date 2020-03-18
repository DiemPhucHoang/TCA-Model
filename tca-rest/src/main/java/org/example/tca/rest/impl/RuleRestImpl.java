package org.example.tca.rest.impl;

import org.example.tca.rest.RuleRest;
import org.example.tca.rest.TCARestUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.RuleVO;

import javax.ws.rs.core.Response;

public class RuleRestImpl implements RuleRest {

    private CentralizedService m_service;

    public RuleRestImpl(CentralizedService m_service) {
        this.m_service = m_service;
    }

    @Override
    public Response listRule(String name, String family, String objectType, String tcaLable) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getRules(name, family, objectType, tcaLable))
                    .build();
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Get all rules for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " failed", e);
        }
    }

    @Override
    public Response getRule(String name, String family, String objectType, String tcaLable, Long id) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getRule(name, family, objectType, tcaLable, id))
                    .build();
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Get rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " failed", e);
        }
    }

    @Override
    public Response addRule(String name, String family, String objectType, String tcaLable, RuleVO ruleVO) {
        try {
            m_service.addRule(name, family, objectType, tcaLable, ruleVO);
            return TCARestUtil.printPassResponse("Added rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Add rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " failed", e);
        }
    }

    @Override
    public Response updateRule(String name, String family, String objectType, String tcaLable, Long id, RuleVO ruleVO) {
        try {
            m_service.updateRule(name, family, objectType, tcaLable, id, ruleVO);
            return TCARestUtil.printPassResponse("Updated rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Update rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " failed", e);
        }
    }

    @Override
    public Response deleteRule(String name, String family, String objectType, String tcaLable, Long id) {
        try {
            m_service.deleteRule(name, family, objectType, tcaLable, id);
            return TCARestUtil.printPassResponse("Deleted rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Delete rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " failed", e);
        }
    }

    @Override
    public Response deleteAllRule(String name, String family, String objectType, String tcaLable) {
        try {
            m_service.deleteAllRule(name, family, objectType, tcaLable);
            return TCARestUtil.printPassResponse("Deleted all rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Delete all rule for " + TCARestUtil.printPath(name, family, objectType, tcaLable) + " failed", e);
        }
    }
}
