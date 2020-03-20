package org.example.tca.rest;

import org.example.tca.vo.RuleVO;

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
public interface RuleRest {

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules")
    @GET
    Response listRule(@PathParam("name") String name, @PathParam("family") String family,
                      @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}")
    @GET
    Response getRule(@PathParam("name") String name, @PathParam("family") String family,
                     @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                     @PathParam("id") Long id);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules")
    @Consumes("application/json")
    @POST
    Response addRule(@PathParam("name") String name, @PathParam("family") String family,
                     @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel, RuleVO ruleVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}")
    @Consumes("application/json")
    @PUT
    Response updateRule(@PathParam("name") String name, @PathParam("family") String family,
                        @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                        @PathParam("id") Long id, RuleVO ruleVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules/{id}")
    @DELETE
    Response deleteRule(@PathParam("name") String name, @PathParam("family") String family,
                        @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel,
                        @PathParam("id") Long id);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}/rules")
    @DELETE
    Response deleteAllRule(@PathParam("name") String name, @PathParam("family") String family,
                           @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel);
}
