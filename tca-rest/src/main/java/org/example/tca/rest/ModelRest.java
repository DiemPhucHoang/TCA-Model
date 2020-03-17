package org.example.tca.rest;

import org.example.tca.vo.ModelVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/models")
public interface ModelRest {

    @Path("/")
    @Produces("application/json")
    @GET
    Collection<ModelVO> listModels();

    @Path("/{name}/{family}")
    @Produces("application/json")
    @GET
    ModelVO getModel(@PathParam("name") String name, @PathParam("family") String family);

    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    Response addModel(ModelVO modelVO);

    @Path("/{name}/{family}")
    @Consumes("application/json")
    @Produces("application/json")
    @PUT
    Response updateModel(@PathParam("name") String name, @PathParam("family") String family, ModelVO modelVO);

    @Path("/{name}/{family}")
    @Produces("application/json")
    @DELETE
    Response deleteModel(@PathParam("name") String name, @PathParam("family") String family);

    @Path("/")
    @Produces("application/json")
    @DELETE
    Response deleteAllModel();
}
