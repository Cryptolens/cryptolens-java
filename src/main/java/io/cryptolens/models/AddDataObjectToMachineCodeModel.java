package io.cryptolens.models;

public class AddDataObjectToMachineCodeModel extends ProductKeyMachineCodeModel {

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

    public AddDataObjectToMachineCodeModel(int productId, String key, String machineCode, String name, int intValue, String stringValue) {
        Name = name;
        IntValue = intValue;
        StringValue = stringValue;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
    }
}
