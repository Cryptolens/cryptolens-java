package io.cryptolens.models;

/**
 * Used to store the error message obtained from the Web API or the client library
 * (eg. in case there is a network error).
 */
public class APIError {

    public ErrorType errorType;
    public String message;

    public APIError() { message = ""; }

    public APIError(String message, ErrorType errorType) {
        this.message = message;
        this.errorType = errorType;
    }
}
