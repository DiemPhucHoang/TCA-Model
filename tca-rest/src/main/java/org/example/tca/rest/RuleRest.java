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
import javax.ws.rs.core.Response;

@Path("/models")
public interface RuleRest {
    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules")
    @Produces("application/json")
    @GET
    Response listRule(@PathParam("name") String name, @PathParam("family") String family,
                      @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}")
    @Produces("application/json")
    @GET
    Response getRule(@PathParam("name") String name, @PathParam("family") String family,
                     @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable, @PathParam("id") Long id);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    Response addRule(@PathParam("name") String name, @PathParam("family") String family,
                     @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable, RuleVO ruleVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    @PUT
    Response updateRule(@PathParam("name") String name, @PathParam("family") String family, @PathParam("objectType") String objectType,
                        @PathParam("tcaLable") String tcaLable, @PathParam("id") Long id, RuleVO ruleVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules/{id}")
    @Produces("application/json")
    @DELETE
    Response deleteRule(@PathParam("name") String name, @PathParam("family") String family,
                        @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable, @PathParam("id") Long id);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}/rules")
    @Produces("application/json")
    @DELETE
    Response deleteAllRule(@PathParam("name") String name, @PathParam("family") String family,
                           @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable);
}
