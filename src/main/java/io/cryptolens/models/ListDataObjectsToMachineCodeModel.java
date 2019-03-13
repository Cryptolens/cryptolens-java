package io.cryptolens.models;

public class ListDataObjectsToMachineCodeModel extends ProductKeyMachineCodeModel {

    /**
     * Shows only Data Objects where the name contains the following string.
     */
    public String Contains = "";

    public ListDataObjectsToMachineCodeModel(int productId, String key, String machineCode, String contains) {
        Contains = contains;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
    }

}
