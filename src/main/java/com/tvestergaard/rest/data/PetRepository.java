package com.tvestergaard.rest.data;

import com.tvestergaard.rest.entities.Pet;

import java.util.Date;
import java.util.List;

public interface PetRepository
{

    /**
     * Returns all the pets in the system.
     *
     * @return A list containing all the pets in the system.
     */
    List<Pet> get();

    /**
     * Returns the pet with the provided id.
     *
     * @param id The id of the pet to retrieve.
     * @return The pet with the provided id, {@code null} when no such entity exists.
     */
    Pet get(int id);

    /**
     * Returns the number of pets in the system.
     *
     * @return The number of pets in the system.
     */
    long count();

    /**
     * Returns all the living pets in the system.
     *
     * @return The living pets in the system.
     */
    List<Pet> getLiving();

    /**
     * Returns all the dead pets in the system
     *
     * @return The dead pets in the system.
     */
    List<Pet> getDead();

    /**
     * Returns all the pets with an event on the provided date.
     *
     * @param date
     * @return The pets with an event on the provided date.
     */
    List<Pet> getByEventDate(Date date);
}
