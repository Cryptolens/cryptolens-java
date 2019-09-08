package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;
import io.cryptolens.internal.BasicResult;

import java.util.List;

public class GetProductsResult extends BasicResult {

    @SerializedName(value = "products", alternate = {"Products"})
    public List<Product> Products;

}
