package com.tvestergaard.rest.api;

import com.tvestergaard.rest.entities.Pet;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.stream.Collectors;

public class PetDTO
{

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

    public int                  id;
    public String               name;
    public String               birth;
    public String               species;
    public String               death;
    public OwnerDTO             owner;
    public Collection<EventDTO> events;

    public PetDTO(Pet pet, boolean setOwner, boolean setEvents)
    {
        this.id = pet.getId();
        this.name = pet.getName();
        this.birth = pet.getBirth() == null ? null : dateFormat.format(pet.getBirth());
        this.species = pet.getSpecies();
        this.death = pet.getDeath() == null ? null : dateFormat.format(pet.getDeath());
        if (setOwner)
            this.owner = new OwnerDTO(pet.getOwner(), false);
        if (setEvents)
            this.events = pet.getEvents().stream().map(e -> new EventDTO(e, false)).collect(Collectors.toList());
    }
}
