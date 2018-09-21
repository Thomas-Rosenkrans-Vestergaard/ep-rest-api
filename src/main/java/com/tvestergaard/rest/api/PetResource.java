package com.tvestergaard.rest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tvestergaard.rest.data.JpaPetRepository;
import com.tvestergaard.rest.data.PetRepository;
import com.tvestergaard.rest.entities.Pet;

import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("pet")
public class PetResource
{

    private static Gson          gson       = new GsonBuilder().setPrettyPrinting().create();
    private static PetRepository repository = new JpaPetRepository(Persistence.createEntityManagerFactory("rest-api-pu"));

    @GET
    @Produces(APPLICATION_JSON)
    public Response get()
    {
        List<Pet> pets = repository.get();
        String    json = gson.toJson(pets.stream().map(p -> new PetDTO(p, true, false)).collect(Collectors.toList()));
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("{id: [0-9]+}")
    @Produces(APPLICATION_JSON)
    public Response get(@PathParam("id") int id)
    {
        Pet pet = repository.get(id);
        if (pet == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        PetDTO petDTO = new PetDTO(pet, true, false);
        String json   = gson.toJson(petDTO);
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("count")
    @Produces(APPLICATION_JSON)
    public Response count()
    {
        long       count      = repository.count();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", Long.valueOf(count));
        return Response.ok().entity(jsonObject.toString()).build();
    }

    @GET
    @Path("living")
    @Produces(APPLICATION_JSON)
    public Response living()
    {
        List<Pet> pets = repository.getLiving();
        String    json = gson.toJson(pets.stream().map(p -> new PetDTO(p, true, false)).collect(Collectors.toList()));
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("dead")
    @Produces(APPLICATION_JSON)
    public Response dead()
    {
        List<Pet> pets = repository.getDead();
        String    json = gson.toJson(pets.stream().map(p -> new PetDTO(p, true, false)).collect(Collectors.toList()));
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("event/{year}/{month}/{day}")
    @Produces(APPLICATION_JSON)
    public Response eventDate(@PathParam("year") int year, @PathParam("month") int month, @PathParam("day") int day)
    {
        Date date = new Date(year - 1900, month - 1, day);

        List<Pet> pets = repository.getByEventDate(date);
        String    json = gson.toJson(pets.stream().map(p -> new PetDTO(p, true, false)).collect(Collectors.toList()));
        return Response.ok().entity(json).build();
    }
}
