package io.cryptolens.models;

public enum ErrorType {
    NotSpecified (0),
    WebAPIError (1),
    LibraryError (2);

    public final int error;

    ErrorType (final int errorValue) {
        error = errorValue;
    }

    public int getError() {
        return error;
    }
}
