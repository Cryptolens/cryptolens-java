package io.cryptolens.models;

public class SetStringValueToKeyModel extends ProductAndKeyModel {

    /**
     * The unique object id for the data object.
     */
    public long Id;

    public String Name;

    /**
     * A string value (text) to store. Max 10000 characters.
     */
    public String StringValue;

    public SetStringValueToKeyModel(int productId, String key, long id, String stringValue) {
        Id = id;
        StringValue = stringValue;
        this.ProductId = productId;
        this.Key = key;
        this.Name = "";
    }
    public SetStringValueToKeyModel(int productId, String key, String name, String stringValue) {
        Id = 0;
        StringValue = stringValue;
        this.ProductId = productId;
        this.Key = key;
        this.Name = name;
    }
}
