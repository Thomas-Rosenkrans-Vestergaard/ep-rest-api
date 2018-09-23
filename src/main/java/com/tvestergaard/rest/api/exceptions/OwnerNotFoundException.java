package com.tvestergaard.rest.api.exceptions;

public class OwnerNotFoundException extends APIException
{

    public OwnerNotFoundException()
    {
        super("Owner with the provided identifier could not be located.", 404);
    }
}
