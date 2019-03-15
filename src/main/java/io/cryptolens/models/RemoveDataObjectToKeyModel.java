package io.cryptolens.models;

public class RemoveDataObjectToKeyModel extends ProductAndKeyModel {

    /**
     * The unique id of the data object to be removed.
     */
    public long Id;

    public String Name;

    public RemoveDataObjectToKeyModel(int productId, String key, long id) {
        Id = id;
        this.ProductId = productId;
        this.Key = key;
        this.Name = "";
    }

    public RemoveDataObjectToKeyModel(int productId, String key, String name) {
        Id = 0;
        this.ProductId = productId;
        this.Key = key;
        this.Name = name;
    }
}
