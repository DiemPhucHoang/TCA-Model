package org.example.tca.rest;

import org.example.tca.vo.ConditionVO;
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

@Path("/models")
public interface ConditionRest {
    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}/conditions")
    @Produces("application/json")
    @GET
    Response listCondition(@PathParam("name") String name, @PathParam("family") String family,
                           @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                           @PathParam("id") Long id);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}/conditions/{attributeName}")
    @Produces("application/json")
    @GET
    Response getCondition(@PathParam("name") String name, @PathParam("family") String family, @PathParam("objectType") String objectType,
                          @PathParam("tcaLable") String tcaLable,
                          @PathParam("id") Long id, @PathParam("attributeName") String attributeName);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}/conditions")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    Response addCondition(@PathParam("name") String name, @PathParam("family") String family,
                          @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                          @PathParam("id") Long id, ConditionVO conditionVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}/conditions/{attributeName}")
    @Consumes("application/json")
    @Produces("application/json")
    @PUT
    Response updateCondition(@PathParam("name") String name, @PathParam("family") String family,
                             @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                             @PathParam("id") Long id, @PathParam("attributeName") String attributeName, ConditionVO conditionVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}/conditions/{attributeName}")
    @Produces("application/json")
    @DELETE
    Response deleteCondition(@PathParam("name") String name, @PathParam("family") String family,
                            @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                            @PathParam("id") Long id, @PathParam("attributeName") String attributeName);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}/conditions")
    @Produces("application/json")
    @DELETE
    Response deleteAllCondition(@PathParam("name") String name, @PathParam("family") String family,
                                @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable,
                                @PathParam("id") Long id);
}

