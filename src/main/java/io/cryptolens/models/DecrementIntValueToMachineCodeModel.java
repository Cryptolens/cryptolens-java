package io.cryptolens.models;

public class DecrementIntValueToMachineCodeModel extends ProductKeyMachineCodeModel {
    public long Id;
    public int IntValue;
    public boolean EnableBound;
    public int Bound;

    public DecrementIntValueToMachineCodeModel(int productId, String key, String machineCode, long id, int intValue, boolean enableBound, int bound) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
        this.MachineCode = machineCode;
    }

}
