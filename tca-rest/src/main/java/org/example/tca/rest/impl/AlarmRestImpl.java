package org.example.tca.rest.impl;

import org.example.tca.rest.AlarmRest;
import org.example.tca.rest.TCARestUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.AlarmVO;

import javax.ws.rs.core.Response;

public class AlarmRestImpl implements AlarmRest {

    private CentralizedService m_service;

    public AlarmRestImpl(CentralizedService service) {
        m_service = service;
    }

    @Override
    public Response getAlarm(String name, String family, String objectType, String tcaLable, Long idRule, Long idAlarm) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getAlarm(name, family, objectType, tcaLable, idRule, idAlarm))
                    .build();
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Get alarm for " + TCARestUtil.printPath(name, family, objectType, tcaLable, idRule, idAlarm) + " failed", e);
        }
    }

    @Override
    public Response addAlarm(String name, String family, String objectType, String tcaLable, Long idRule, AlarmVO alarmVO) {
        try {
            m_service.addAlarm(name, family, objectType, tcaLable, idRule, alarmVO);
            return TCARestUtil.printPassResponse("Added alarm for " + TCARestUtil.printPath(name, family, objectType, tcaLable, idRule) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Added alarm for " + TCARestUtil.printPath(name, family, objectType, tcaLable, idRule) + " failed", e);
        }
    }

    @Override
    public Response updateAlarm(String name, String family, String objectType, String tcaLable, Long idRule, Long idAlarm, AlarmVO alarmVO) {
        try {
            m_service.updateAlarm(name, family, objectType, tcaLable, idRule, idAlarm, alarmVO);
            return TCARestUtil.printPassResponse("Updated alarm for " + TCARestUtil.printPath(name, family, objectType, tcaLable, idRule) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Updated alarm for " + TCARestUtil.printPath(name, family, objectType, tcaLable, idRule) + " failed", e);
        }
    }

    @Override
    public Response deleteAlarm(String name, String family, String objectType, String tcaLable, Long idRule, Long idAlarm) {
        try {
            m_service.deleteAlarm(name, family, objectType, tcaLable, idRule, idAlarm);
            return TCARestUtil.printPassResponse("Deleted alarm for " + TCARestUtil.printPath(name, family, objectType, tcaLable, idRule, idAlarm) + " successfully");
        } catch (Exception e) {
            return TCARestUtil.printFailResponse("Deleted alarm for " + TCARestUtil.printPath(name, family, objectType, tcaLable, idRule, idAlarm) + " failed", e);
        }
    }
}

