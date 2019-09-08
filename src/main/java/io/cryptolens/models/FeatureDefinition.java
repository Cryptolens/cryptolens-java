package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

public class FeatureDefinition {

    @SerializedName(value = "name", alternate = {"Name"})
    public String Name;

    @SerializedName(value = "type", alternate = {"Type"})
    public FeatureType Type;

    public FeatureDefinition(String name, FeatureType type) {
        Name = name;
        Type = type;
    }
}
