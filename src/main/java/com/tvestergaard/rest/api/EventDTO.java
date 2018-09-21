package com.tvestergaard.rest.api;

import com.tvestergaard.rest.entities.Event;

import java.text.SimpleDateFormat;

public class EventDTO
{

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

    public int    id;
    public String event;
    public String remark;
    public String date;
    public PetDTO pet;

    public EventDTO(Event event, boolean setPet)
    {
        this.id = event.getId();
        this.event = event.getEvent();
        this.remark = event.getRemark();
        this.date = event.getDate() == null ? null : dateFormat.format(event.getDate());
        if (setPet)
            this.pet = new PetDTO(event.getPet(), true, false);
    }
}
