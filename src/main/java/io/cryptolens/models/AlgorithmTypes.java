package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

public enum AlgorithmTypes {
    @SerializedName("0")
    SKGL (0),
    @SerializedName("1")
    SKM15 (1);

    private final int value;
    AlgorithmTypes(final int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
