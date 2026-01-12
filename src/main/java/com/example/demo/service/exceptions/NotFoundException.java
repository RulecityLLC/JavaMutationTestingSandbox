package com.example.demo.service.exceptions;

/**
 * If a user was not found
 */
public class NotFoundException extends Exception
{
    public NotFoundException(Throwable cause) { super(cause); }
}
