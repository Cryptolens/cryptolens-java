package io.cryptolens.models;

public class IncrementIntValueToMachineCodeModel extends ProductKeyMachineCodeModel {
    public long Id;
    public int IntValue;
    public boolean EnableBound;
    public int Bound;

    public IncrementIntValueToMachineCodeModel(int productId, String key, String machineCode, long id, int intValue, boolean enableBound, int bound) {
        Id = id;
        this.IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
        this.MachineCode = machineCode;
    }
}
