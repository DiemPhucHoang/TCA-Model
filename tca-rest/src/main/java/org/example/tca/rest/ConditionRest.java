package org.example.tca.rest;

import org.example.tca.vo.ConditionVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/models")
@Produces(MediaType.APPLICATION_JSON)
public interface ConditionRest {
    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}/conditions")
    @GET
    Response listCondition(@PathParam("name") String name, @PathParam("family") String family,
                           @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                           @PathParam("id") Long id);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}/conditions/{attributeName}")
    @GET
    Response getCondition(@PathParam("name") String name, @PathParam("family") String family, @PathParam("objectType") String objectType,
                          @PathParam("tcaLabel") String tcaLabel,
                          @PathParam("id") Long id, @PathParam("attributeName") String attributeName);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}/conditions")
    @Consumes("application/json")
    @POST
    Response addCondition(@PathParam("name") String name, @PathParam("family") String family,
                          @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                          @PathParam("id") Long id, ConditionVO conditionVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}/conditions/{attributeName}")
    @Consumes("application/json")
    @PUT
    Response updateCondition(@PathParam("name") String name, @PathParam("family") String family,
                             @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                             @PathParam("id") Long id, @PathParam("attributeName") String attributeName, ConditionVO conditionVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}/conditions/{attributeName}")
    @DELETE
    Response deleteCondition(@PathParam("name") String name, @PathParam("family") String family,
                            @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                            @PathParam("id") Long id, @PathParam("attributeName") String attributeName);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}/conditions")
    @DELETE
    Response deleteAllCondition(@PathParam("name") String name, @PathParam("family") String family,
                                @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                                @PathParam("id") Long id);
}

