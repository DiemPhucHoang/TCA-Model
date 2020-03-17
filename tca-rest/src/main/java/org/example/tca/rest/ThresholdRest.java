package org.example.tca.rest;


import org.example.tca.vo.ThresholdVO;

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
public interface ThresholdRest {

    @Path("/{name}/{family}/thresholds")
    @Produces("application/json")
    @GET
    Response listThreshold(@PathParam("name") String name, @PathParam("family") String family);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}")
    @Produces("application/json")
    @GET
    Response getThreshold(@PathParam("name") String name, @PathParam("family") String family,
                          @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable);

    @Path("/{name}/{family}/thresholds")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    Response addThreshold(@PathParam("name") String name, @PathParam("family") String family, ThresholdVO thresholdVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}")
    @Consumes("application/json")
    @Produces("application/json")
    @PUT
    Response updateThreshold(@PathParam("name") String name, @PathParam("family") String family,
                             @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable, ThresholdVO thresholdVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLable}")
    @Produces("application/json")
    @DELETE
    Response deleteThreshold(@PathParam("name") String name, @PathParam("family") String family,
                             @PathParam("objectType") String objectType, @PathParam("tcaLable") String tcaLable);

    @Path("/{name}/{family}/thresholds")
    @Produces("application/json")
    @DELETE
    Response deleteAllThreshold(@PathParam("name") String name, @PathParam("family") String family);

}
