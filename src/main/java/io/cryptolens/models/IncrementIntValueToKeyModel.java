package io.cryptolens.models;

public class IncrementIntValueToKeyModel extends ProductAndKeyModel {
    public long Id;
    public int IntValue;
    public boolean EnableBound;
    public int Bound;

    public IncrementIntValueToKeyModel(int productId, String key, long id, int intValue, boolean enableBound, int bound) {
        Id = id;
        this.IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
    }
}
