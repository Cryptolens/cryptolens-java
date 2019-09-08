package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class Product {
    @SerializedName(value = "id", alternate = {"Id"})
    public int Id;

    @SerializedName(value = "name", alternate = {"Name"})
    public String Name;
    @SerializedName(value = "creationDate", alternate = {"CreationDate"})
    public LocalDateTime CreationDate;

    @SerializedName(value = "description", alternate = {"Description"})
    public String Description;
    @SerializedName(value = "password", alternate = {"Password"})
    public String Password;

    @SerializedName(value = "isPublic", alternate = {"IsPublic"})
    public boolean IsPublic;
    @SerializedName(value = "keyAlgorithm", alternate = {"KeyAlgorithm"})
    public AlgorithmTypes KeyAlgorithm;
    @SerializedName(value = "featureDefinitions", alternate = {"FeatureDefinitions"})
    public FeatureDefinitions FeatureDefinitions;
    @SerializedName(value = "dataObjects", alternate = {"DataObjects"})
    public List<DataObject> DataObjects;

    public Product(int id, String name, LocalDateTime creationDate, String description, String password, boolean isPublic, AlgorithmTypes keyAlgorithm, io.cryptolens.models.FeatureDefinitions featureDefinitions, List<DataObject> dataObjects) {
        Id = id;
        Name = name;
        CreationDate = creationDate;
        Description = description;
        Password = password;
        IsPublic = isPublic;
        KeyAlgorithm = keyAlgorithm;
        FeatureDefinitions = featureDefinitions;
        DataObjects = dataObjects;
    }
}
