package io.cryptolens.models;

public class DeactivateModel {

    public int ProductId;
    public String Key;
    public String MachineCode;
    public boolean Floating;

    public DeactivateModel() {

    }

    public DeactivateModel(int productId, String key, String machineCode) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
    }

    public DeactivateModel(int productId, String key, String machineCode, boolean floating) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        Floating = floating;
    }
}
