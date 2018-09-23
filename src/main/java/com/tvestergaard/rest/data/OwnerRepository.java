package com.tvestergaard.rest.data;

import com.tvestergaard.rest.entities.Owner;

import java.util.List;

public interface OwnerRepository
{

    /**
     * Finds all the owners in the system.
     *
     * @return The complete list of the owners in the system.
     */
    List<Owner> get();

    /**
     * Returns the owner with the provided id.
     *
     * @param id The id of the owner to return.
     * @return The owner with the provided id, {@code null} when no such entity exists.
     */
    Owner get(int id);
}
