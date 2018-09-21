package com.tvestergaard.rest.api;

import com.tvestergaard.rest.entities.Pet;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class PetDTO
{
    public int                  id;
    public String               name;
    public Date                 birth;
    public String               species;
    public Date                 death;
    public OwnerDTO             owner;
    public Collection<EventDTO> events;

    public PetDTO(Pet pet, boolean setOwner, boolean setEvents)
    {
        this.id = pet.getId();
        this.name = pet.getName();
        this.birth = pet.getBirth();
        this.species = pet.getSpecies();
        this.death = pet.getDeath();
        if (setOwner)
            this.owner = new OwnerDTO(pet.getOwner(), false);
        if (setEvents)
            this.events = pet.getEvents().stream().map(e -> new EventDTO(e, false)).collect(Collectors.toList());
    }
}
