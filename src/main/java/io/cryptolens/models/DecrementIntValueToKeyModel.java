package io.cryptolens.models;

public class DecrementIntValueToKeyModel extends ProductAndKeyModel {
    public long Id;
    public int IntValue;
    public boolean EnableBound;
    public int Bound;

    public DecrementIntValueToKeyModel(int productId, String key, long id, int intValue, boolean enableBound, int bound) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
    }
}
