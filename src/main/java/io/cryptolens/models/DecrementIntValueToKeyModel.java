package io.cryptolens.models;

public class DecrementIntValueToKeyModel extends ProductAndKeyModel {
    public long Id;
    public int IntValue;

    public DecrementIntValueToKeyModel(int productId, String key, long id, int intValue) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
    }
}
