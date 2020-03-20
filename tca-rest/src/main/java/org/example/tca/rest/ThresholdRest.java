package org.example.tca.rest;

import org.example.tca.vo.ThresholdVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/models")
@Produces(MediaType.APPLICATION_JSON)
public interface ThresholdRest {

    @Path("/{name}/{family}/thresholds")
    @GET
    Response listThreshold(@PathParam("name") String name, @PathParam("family") String family);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}")
    @GET
    Response getThreshold(@PathParam("name") String name, @PathParam("family") String family,
                          @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel);

    @Path("/{name}/{family}/thresholds")
    @Consumes("application/json")
    @POST
    Response addThreshold(@PathParam("name") String name, @PathParam("family") String family, ThresholdVO thresholdVO);

    @Path("/{name}/{family}/thresholds/{objectType}/{tcaLabel}")
    @DELETE
    Response deleteThreshold(@PathParam("name") String name, @PathParam("family") String family,
                             @PathParam("objectType") String objectType, @PathParam("tcaLabel") String tcaLabel);

    @Path("/{name}/{family}/thresholds")
    @DELETE
    Response deleteAllThreshold(@PathParam("name") String name, @PathParam("family") String family);

}
