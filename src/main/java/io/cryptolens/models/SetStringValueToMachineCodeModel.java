package io.cryptolens.models;

public class SetStringValueToMachineCodeModel extends ProductKeyMachineCodeModel {
    public long Id;
    public String StringValue;

    public SetStringValueToMachineCodeModel(int productId, String key, String machineCode, long id, String stringValue) {
        Id = id;
        StringValue = stringValue;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
    }
}
