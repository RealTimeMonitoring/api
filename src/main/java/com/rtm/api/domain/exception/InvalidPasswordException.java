package com.rtm.api.domain.exception;

public class InvalidPasswordException extends RuntimeException 
{
    public InvalidPasswordException( String message )
    {
        super( message );
    }
}