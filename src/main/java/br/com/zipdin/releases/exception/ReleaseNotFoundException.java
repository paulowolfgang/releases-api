package br.com.zipdin.releases.exception;

public class ReleaseNotFoundException extends RuntimeException
{
    public ReleaseNotFoundException(String message)
    {
        super(message);
    }
}
