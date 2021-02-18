package io.cryptolens.models;

public class CreateTrialKeyModel extends RequestModel {
    public int ProductId;
    public String MachineCode;

    public CreateTrialKeyModel(int productId, String machineCode) {
        ProductId = productId;
        MachineCode = machineCode;
    }
}
