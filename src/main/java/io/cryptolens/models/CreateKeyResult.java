package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;
import io.cryptolens.internal.BasicResult;

public class CreateKeyResult extends BasicResult {
    @SerializedName(value = "key", alternate = {"Key"})
    public String Key;
}
