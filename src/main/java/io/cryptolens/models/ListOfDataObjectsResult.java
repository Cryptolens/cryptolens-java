package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;
import io.cryptolens.internal.BasicResult;

import java.util.List;

public class ListOfDataObjectsResult extends BasicResult {
    @SerializedName(value = "dataObjects", alternate = {"DataObjects"})
    public List<DataObject> DataObjects;
}
