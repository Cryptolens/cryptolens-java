package io.cryptolens.models;

public class ListDataObjectsToMachineCodeModel extends ProductKeyMachineCodeModel {

    public String Contains = "";

    public ListDataObjectsToMachineCodeModel(int productId, String key, String machineCode, String contains) {
        Contains = contains;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
    }

}
