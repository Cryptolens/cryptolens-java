package io.cryptolens.models;

public class ListDataObjectsToKeyModel extends ProductAndKeyModel {
    public String Contains = "";

    public ListDataObjectsToKeyModel(int productId, String key, String contains) {
        Contains = contains;
        this.ProductId = productId;
        this.Key = key;
    }
}
