package com.tvestergaard.rest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tvestergaard.rest.api.exceptions.OwnerNotFoundException;
import com.tvestergaard.rest.data.JpaOwnerRepository;
import com.tvestergaard.rest.data.OwnerRepository;
import com.tvestergaard.rest.entities.Owner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("owner")
public class OwnerResource
{

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rest-api-pu");
    private static Gson                 gson                 = new GsonBuilder().setPrettyPrinting().create();
    private static OwnerRepository      ownerRepository      = new JpaOwnerRepository(entityManagerFactory);

    @GET
    @Produces(APPLICATION_JSON)
    public Response get()
    {
        return Response.ok().entity(gson.toJson(ownerRepository.get().stream().map(o -> new OwnerDTO(o, false)).collect(Collectors.toList()))).build();
    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces(APPLICATION_JSON)
    public Response one(@PathParam("id") int id) throws Exception
    {
        Owner owner = ownerRepository.get(id);
        if (owner == null)
            throw new OwnerNotFoundException();

        return Response.ok().entity(gson.toJson(new OwnerDTO(owner, false))).build();
    }
}
