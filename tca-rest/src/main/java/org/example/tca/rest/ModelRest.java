package org.example.tca.rest;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.example.tca.vo.ModelVO;

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
import java.util.Collection;

@Path("/models")
@Produces(MediaType.APPLICATION_JSON)
public interface ModelRest {

    @POST
    @Path("/import")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    Response importModel(Attachment attachment);

    @Path("/")
    @GET
    Collection<ModelVO> listModels();

    @Path("/{name}/{family}")
    @GET
    ModelVO getModel(@PathParam("name") String name, @PathParam("family") String family);

    @Path("/{name}/{family}")
    @Consumes("application/json")
    @PUT
    Response updateModel(@PathParam("name") String name, @PathParam("family") String family, ModelVO modelVO);

    @Path("/{name}/{family}")
    @DELETE
    Response deleteModel(@PathParam("name") String name, @PathParam("family") String family);

    @Path("/")
    @DELETE
    Response deleteAllModel();
}
