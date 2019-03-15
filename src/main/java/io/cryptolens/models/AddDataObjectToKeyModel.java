package io.cryptolens.models;

public class AddDataObjectToKeyModel extends ProductAndKeyModel {
    /**
     * The name of the data object. Max 10 characters.
     */
    public String Name;
    /**
     * An int value (int32) to store.
     */
    public int IntValue;
    /**
     * A string value (text) to store. Max 10000 characters.
     */
    public String StringValue;
    /**
     * If set to true, this method will check that no other data object with the same name exists.
     * Note: setting this to true may affect performance.
     */
    public boolean CheckForDuplicates;

    public AddDataObjectToKeyModel(int productId, String key, String name, int intValue, String stringValue) {
        Name = name;
        StringValue = stringValue;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
    }

    public AddDataObjectToKeyModel(int productId, String key, String name, int intValue, String stringValue, boolean checkForDuplicates) {
        Name = name;
        StringValue = stringValue;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.CheckForDuplicates = checkForDuplicates;
    }
}
