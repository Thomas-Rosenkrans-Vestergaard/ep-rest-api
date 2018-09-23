package com.tvestergaard.rest.api.exceptions;

public class EventNotFoundException extends APIException
{

    public EventNotFoundException()
    {
        super("Event with the provided identifier could not be located.", 404);
    }
}
