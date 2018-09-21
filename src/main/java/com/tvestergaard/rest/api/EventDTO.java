package com.tvestergaard.rest.api;

import com.tvestergaard.rest.entities.Event;

import java.util.Date;

public class EventDTO
{

    public int    id;
    public String event;
    public String remark;
    public Date   date;
    public PetDTO pet;

    public EventDTO(Event event, boolean setPet)
    {
        this.id = event.getId();
        this.event = event.getEvent();
        this.remark = event.getRemark();
        this.date = event.getDate();
        if (setPet)
            this.pet = new PetDTO(event.getPet(), true, false);
    }
}
