package io.cryptolens.models;

public class IncrementIntValueToKeyModel extends ProductAndKeyModel {
    public long Id;
    public int IntValue;

    public IncrementIntValueToKeyModel(int productId, String key, long id, int intValue) {
        Id = id;
        this.IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
    }
}
