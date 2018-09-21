package com.tvestergaard.rest.data;

import com.tvestergaard.rest.entities.Event;
import com.tvestergaard.rest.entities.Pet;

import java.util.Date;
import java.util.List;

public interface EventRepository
{

    /**
     * Returns all the events in the system.
     *
     * @return The events in the system.
     */
    List<Event> get();

    /**
     * Returns the event with the provided id.
     *
     * @param id The id of the event to return.
     * @return The event with the provided id, {@code null} when no such entity exists.
     */
    Event get(int id);

    /**
     * Returns the events associated with the provided pet id.
     *
     * @param pet The pet id to return the events of.
     * @return The events associated with the provided pet id, {@code null} when the provided pet id does not exist.
     */
    List<Event> getFromPet(int pet);

    /**
     * Creates and returns a new event.
     *
     * @param pet    The pet the event belongs to.
     * @param event  The event string.
     * @param remark The remark string.
     * @param date   The date the event transpired on.
     * @return The event entity.
     */
    Event create(Pet pet, String event, String remark, Date date);
}
