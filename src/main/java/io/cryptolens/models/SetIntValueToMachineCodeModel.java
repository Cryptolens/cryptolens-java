package io.cryptolens.models;

public class SetIntValueToMachineCodeModel extends ProductKeyMachineCodeModel {

    /**
     * The unique object id for the data object.
     */
    public long Id;
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
    }
}
