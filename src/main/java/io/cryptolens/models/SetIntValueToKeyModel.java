package io.cryptolens.models;

public class SetIntValueToKeyModel extends ProductAndKeyModel {
    /**
     * The unique object id for the data object.
     */
    public long Id;

    public String Name;
    /**
     * The new int value that should be assigned to the data object.
     */
    public int IntValue;

    public SetIntValueToKeyModel(int productId, String key, long id, int intValue) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.Name = "";
    }

    public SetIntValueToKeyModel(int productId, String key,String name, int intValue) {
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.Name = name;
        Id = 0;
    }
}
