package io.cryptolens.internal;

import com.google.gson.annotations.SerializedName;

public class BasicResult {

    @SerializedName(value = "result", alternate = {"Result"})
    public int result;

    @SerializedName(value = "message", alternate = {"Message"})
    public String message;

    @SerializedName(value = "rawResponse", alternate = {"RawResponse"})
    public String RawResponse;
}
