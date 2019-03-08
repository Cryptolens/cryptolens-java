package io.cryptolens.models;

public class SetIntValueToMachineCodeModel extends ProductKeyMachineCodeModel {

    public long Id;
    public int IntValue;

    public SetIntValueToMachineCodeModel(int productId, String key, String machineCode, long id, int intValue) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
    }
}
