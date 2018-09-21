package com.tvestergaard.rest.api;

import com.tvestergaard.rest.entities.Owner;

import java.util.Collection;
import java.util.stream.Collectors;

public class OwnerDTO
{

    private int                id;
    private String             firstName;
    private String             lastName;
    private String             address;
    private Collection<PetDTO> pets;

    public OwnerDTO(Owner owner, boolean acceptPets)
    {
        this.id = owner.getId();
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
        this.address = owner.getAddress();
        if (acceptPets)
            this.pets = owner.getPets().stream().map(p -> new PetDTO(p, false, true)).collect(Collectors.toList());
    }
}
