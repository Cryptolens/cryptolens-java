package io.cryptolens.models;

public class ListDataObjectsToKeyModel extends ProductAndKeyModel {

    /**
     * Shows only Data Objects where the name contains the following string.
     */
    public String Contains = "";

    public ListDataObjectsToKeyModel(int productId, String key, String contains) {
        Contains = contains;
        this.ProductId = productId;
        this.Key = key;
    }
}
