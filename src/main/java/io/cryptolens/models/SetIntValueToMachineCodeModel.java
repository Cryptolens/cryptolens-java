package io.cryptolens.models;

public class SetIntValueToMachineCodeModel extends ProductKeyMachineCodeModel {

    /**
     * The unique object id for the data object.
     */
    public long Id;

    public String Name;
    /**
     * The new int value that should be assigned to the data object.
     */
    public int IntValue;


    public SetIntValueToMachineCodeModel(int productId, String key, String machineCode, long id, int intValue) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
        this.Name = "";
    }
    public SetIntValueToMachineCodeModel(int productId, String key, String machineCode, String name, int intValue) {
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
        this.Name = name;
        Id = 0;
    }
}
