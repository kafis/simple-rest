package de.deutschepost.secreq.app;

import de.deutschepost.secreq.domain.SecReq;
import de.deutschepost.secreq.domain.SecReqRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@Path("/secreqs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SecReqCollection {

    private static Logger LOG = Logger.getLogger("requestLog");

    private SecReqRepository repository = new SecReqRepository();

    @Context
    private UriInfo uriInfo;

    @GET
    public Response all() {
        LOG.info("GET");
        return Response.ok(repository.list()).build();
    }

    @POST
    public Response store(SecReq secReq) {
        LOG.info("POST");
        repository.store(secReq);
        return Response.created(uriInfo.getAbsolutePathBuilder()
                                       .path(SecReqCollection.class,"get")
                                       .build(secReq.getId()))
                       .entity(secReq)
                       .build();

    }


    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {
        LOG.info("GET");
        return Response.ok(repository.findById(id))
                       .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") int id, SecReq secReq) {
        LOG.info("PUT");
        repository.update(id, secReq.getDescription());
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        LOG.info("DELETE");
        repository.delete(id);
        return  Response.noContent().build();
    }

}
