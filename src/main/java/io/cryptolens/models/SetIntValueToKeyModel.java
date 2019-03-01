package io.cryptolens.models;

public class SetIntValueToKeyModel extends ProductAndKeyModel {
    public long Id;
    public int IntValue;

    public SetIntValueToKeyModel(int productId, String key, long id, int intValue) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
    }
}
