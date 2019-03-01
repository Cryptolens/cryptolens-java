package io.cryptolens.models;

public class SetStringValueToKeyModel extends ProductAndKeyModel {
    public long Id;
    public String StringValue;

    public SetStringValueToKeyModel(int productId, String key, long id, String stringValue) {
        Id = id;
        StringValue = stringValue;
        this.ProductId = productId;
        this.Key = key;
    }
}
