package io.cryptolens.internal;

import com.google.gson.annotations.SerializedName;

public class ActivateResult extends BasicResult {

    @SerializedName(value = "licenseKey", alternate = {"LicenseKey"})
    public String licenseKey;

    @SerializedName(value = "signature", alternate = {"Signature"})
    public String signature;
}
