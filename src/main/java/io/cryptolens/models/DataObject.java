package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

public class DataObject {
    /**
     * The unique identifier of the data object.
     */
    @SerializedName(value = "id", alternate = {"Id"})
    public int Id;

    /**
     * The name of the data object (max 10 chars).
     */
    @SerializedName(value = "name", alternate = {"Name"})
    public String Name;

    /**
     * A string value associated with the data object (max 10,000 chars).
     */
    @SerializedName(value = "stringValue", alternate = {"StringValue"})
    public String StringValue;

    /**
     * An int32 value associated with the data object.
     */
    @SerializedName(value = "intValue", alternate = {"IntValue"})
    public int IntValue;
}
