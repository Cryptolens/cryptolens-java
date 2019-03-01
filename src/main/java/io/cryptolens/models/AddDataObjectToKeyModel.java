package io.cryptolens.models;

public class AddDataObjectToKeyModel extends ProductAndKeyModel {
    public String Name;
    public int IntValue;
    public String StringValue;

    public AddDataObjectToKeyModel(int productId, String key, String name, int intValue, String stringValue) {
        Name = name;
        StringValue = stringValue;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
    }
}
