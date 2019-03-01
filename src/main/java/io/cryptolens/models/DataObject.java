package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

public class DataObject {
    @SerializedName(value = "id", alternate = {"Id"})
    public int Id;
    @SerializedName(value = "name", alternate = {"Name"})
    public String Name;
    @SerializedName(value = "stringValue", alternate = {"StringValue"})
    public String StringValue;
    @SerializedName(value = "intValue", alternate = {"IntValue"})
    public int IntValue;
}
