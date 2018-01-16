package uy.com.netlabs.exceptions;

/**
 * Created by nicolas on 16/01/18.
 */
public class ErrorProductNotExistsException extends Exception{
    public ErrorProductNotExistsException(){super();}
    public ErrorProductNotExistsException(String message) { super(message); }

}
