package com.tvestergaard.rest.api.exceptions;

public class PetNotFoundException extends APIException
{

    public PetNotFoundException()
    {
        super("Pet with provided identifier could not be located.", 404);
    }
}
