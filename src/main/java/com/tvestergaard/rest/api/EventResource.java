package com.tvestergaard.rest.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tvestergaard.rest.api.exceptions.EventNotFoundException;
import com.tvestergaard.rest.api.exceptions.PetNotFoundException;
import com.tvestergaard.rest.data.EventRepository;
import com.tvestergaard.rest.data.JpaEventRepository;
import com.tvestergaard.rest.data.JpaPetRepository;
import com.tvestergaard.rest.data.PetRepository;
import com.tvestergaard.rest.entities.Event;
import com.tvestergaard.rest.entities.Pet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("event")
public class EventResource
{

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rest-api-pu");
    private static Gson                 gson                 = new GsonBuilder().setPrettyPrinting().create();
    private static PetRepository        petRepository        = new JpaPetRepository(entityManagerFactory);
    private static EventRepository      repository           = new JpaEventRepository(entityManagerFactory);

    @GET
    @Produces(APPLICATION_JSON)
    public Response get()
    {
        List<Event> events = repository.get();
        String      json   = gson.toJson(events.stream().map(e -> new EventDTO(e, true)).collect(Collectors.toList()));
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("{id : [0-9]+}")
    @Produces(APPLICATION_JSON)
    public Response get(@PathParam("id") int id) throws Exception
    {
        Event event = repository.get(id);
        if (event == null)
            throw new EventNotFoundException();

        EventDTO eventDTO = new EventDTO(event, true);
        String   json     = gson.toJson(eventDTO);
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("pet/{pet: [0-9]+}")
    @Produces(APPLICATION_JSON)
    public Response getFromPet(@PathParam("pet") int pet) throws Exception
    {
        List<Event> events = repository.getFromPet(pet);
        if (events == null)
            throw new PetNotFoundException();

        String json = gson.toJson(events.stream().map(e -> new EventDTO(e, false)).collect(Collectors.toList()));
        return Response.ok().entity(json).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response create(String json) throws Exception
    {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");

        PostBody request  = gson.fromJson(json, PostBody.class);
        Pet      pet      = petRepository.get(request.pet);
        Event    event    = repository.create(pet, request.event, request.remark, format.parse(request.date));
        String   response = gson.toJson(new EventDTO(event, true));

        return Response.status(CREATED).entity(response).build();
    }

    private class PostBody
    {
        public int    pet;
        public String event;
        public String remark;
        public String date;
    }
}
