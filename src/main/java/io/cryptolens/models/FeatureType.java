package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

public enum FeatureType {
    @SerializedName("0")
    Default (0),
    @SerializedName("0")
    TimeLimitedFeature (1),
    @SerializedName("2")
    TrialFeature(2);

    private final int value;
    FeatureType(final int i) {
        value = i;
    }
    public int getValue() { return value;}
}
