package org.example.tca.rest;

import org.example.tca.vo.AlarmVO;
import org.example.tca.vo.RuleVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface AlarmRest {
    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{idRule}/alarm/{idAlarm}")
    @Produces("application/json")
    @GET
    Response getAlarm(@PathParam("name") String name, @PathParam("family") String family,
                     @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                     @PathParam("idRule") Long idRule, @PathParam("idAlarm") Long idAlarm);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{idRule}/alarm")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    Response addAlarm(@PathParam("name") String name, @PathParam("family") String family,
                      @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                      @PathParam("idRule") Long idRule, AlarmVO alarmVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{idRule}/alarm/{idAlarm}")
    @Consumes("application/json")
    @Produces("application/json")
    @PUT
    Response updateAlarm(@PathParam("name") String name, @PathParam("family") String family,
                         @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                         @PathParam("idRule") Long idRule, @PathParam("idAlarm") Long idAlarm, AlarmVO alarmVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{idRule}/alarm/{idAlarm}")
    @Produces("application/json")
    @DELETE
    Response deleteAlarm(@PathParam("name") String name, @PathParam("family") String family,
                         @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                         @PathParam("idRule") Long idRule, @PathParam("idAlarm") Long idAlarm);
}
