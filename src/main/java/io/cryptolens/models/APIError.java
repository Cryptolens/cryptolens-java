package io.cryptolens.models;

/**
 * Used to store the error message obtained from the Web API.
 */
public class APIError {
    public String Message;

    public APIError() { Message = ""; }

    public APIError(String message) {
        Message = message;
    }
}
