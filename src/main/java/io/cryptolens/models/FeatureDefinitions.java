package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

public class FeatureDefinitions {
    @SerializedName(value = "f1", alternate = {"F1"})
    public FeatureDefinition F1;
    @SerializedName(value = "f2", alternate = {"F2"})
    public FeatureDefinition F2;
    @SerializedName(value = "f3", alternate = {"F3"})
    public FeatureDefinition F3;
    @SerializedName(value = "f4", alternate = {"F4"})
    public FeatureDefinition F4;
    @SerializedName(value = "f5", alternate = {"F5"})
    public FeatureDefinition F5;
    @SerializedName(value = "f6", alternate = {"F6"})
    public FeatureDefinition F6;
    @SerializedName(value = "f7", alternate = {"F7"})
    public FeatureDefinition F7;
    @SerializedName(value = "f8", alternate = {"F8"})
    public FeatureDefinition F8;

    @SerializedName(value = "allLicensesTimeLimited", alternate = {"AllLicensesTimeLimited"})
    public boolean AllLicensesTimeLimited;
    @SerializedName(value = "blockExpiredLicenses", alternate = {"BlockExpiredLicenses"})
    public boolean BlockExpiredLicenses;

    public FeatureDefinitions(FeatureDefinition f1, FeatureDefinition f2, FeatureDefinition f3, FeatureDefinition f4, FeatureDefinition f5, FeatureDefinition f6, FeatureDefinition f7, FeatureDefinition f8, boolean allLicensesTimeLimited, boolean blockExpiredLicenses) {
        F1 = f1;
        F2 = f2;
        F3 = f3;
        F4 = f4;
        F5 = f5;
        F6 = f6;
        F7 = f7;
        F8 = f8;
        AllLicensesTimeLimited = allLicensesTimeLimited;
        BlockExpiredLicenses = blockExpiredLicenses;
    }
}
