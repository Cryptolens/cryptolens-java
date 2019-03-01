package io.cryptolens.models;

public class RemoveDataObjectToKeyModel extends ProductAndKeyModel {

    /**
     * The unique id of the data object to be removed.
     */
    public long Id;

    public RemoveDataObjectToKeyModel(int productId, String key, long id) {
        Id = id;
        this.ProductId = productId;
        this.Key = key;
    }
}
