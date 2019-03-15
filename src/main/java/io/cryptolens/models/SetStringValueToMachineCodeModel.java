package io.cryptolens.models;

public class SetStringValueToMachineCodeModel extends ProductKeyMachineCodeModel {

    /**
     * The unique object id for the data object.
     */
    public long Id;

    public String Name;


    /**
     * A string value (text) to store. Max 10000 characters.
     */
    public String StringValue;

    public SetStringValueToMachineCodeModel(int productId, String key, String machineCode, long id, String stringValue) {
        Id = id;
        StringValue = stringValue;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
        this.Name = "";
    }

    public SetStringValueToMachineCodeModel(int productId, String key, String machineCode, String name, String stringValue) {
        Id = 0;
        StringValue = stringValue;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
        this.Name = name;
    }
}
