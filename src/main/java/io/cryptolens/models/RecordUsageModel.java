package io.cryptolens.models;

public class RecordUsageModel {
    public int ProductId;
    public String Key;
    public int Amount;

    public RecordUsageModel(int productId, String key, int amount) {
        ProductId = productId;
        Key = key;
        Amount = amount;
    }
}
