package org.example.tca.rest.impl;

import org.example.tca.rest.AlarmRest;
import org.example.tca.response.TCAResponseUtil;
import org.example.tca.service.CentralizedService;
import org.example.tca.vo.AlarmVO;

import javax.ws.rs.core.Response;

public class AlarmRestImpl implements AlarmRest {

    private CentralizedService m_service;

    public AlarmRestImpl(CentralizedService service) {
        m_service = service;
    }

    @Override
    public Response getAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm) {
        try {
            return Response
                    .ok()
                    .entity(m_service.getAlarm(name, family, objectType, tcaLabel, idRule, idAlarm))
                    .build();
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Get alarm for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, idRule, idAlarm) + " failed", e);
        }
    }

    @Override
    public Response addAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, AlarmVO alarmVO) {
        try {
            m_service.addAlarm(name, family, objectType, tcaLabel, idRule, alarmVO);
            return TCAResponseUtil.printPassResponse("Added alarm for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, idRule) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Added alarm for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, idRule) + " failed", e);
        }
    }

    @Override
    public Response updateAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm, AlarmVO alarmVO) {
        try {
            m_service.updateAlarm(name, family, objectType, tcaLabel, idRule, idAlarm, alarmVO);
            return TCAResponseUtil.printPassResponse("Updated alarm for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, idRule) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Updated alarm for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, idRule) + " failed", e);
        }
    }

    @Override
    public Response deleteAlarm(String name, String family, String objectType, String tcaLabel, Long idRule, Long idAlarm) {
        try {
            m_service.deleteAlarm(name, family, objectType, tcaLabel, idRule, idAlarm);
            return TCAResponseUtil.printPassResponse("Deleted alarm for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, idRule, idAlarm) + " successfully");
        } catch (Exception e) {
            return TCAResponseUtil.printFailResponse("Deleted alarm for " + TCAResponseUtil.printPath(name, family, objectType, tcaLabel, idRule, idAlarm) + " failed", e);
        }
    }
}

