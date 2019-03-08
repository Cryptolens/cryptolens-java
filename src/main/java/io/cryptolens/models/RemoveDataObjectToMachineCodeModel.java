package io.cryptolens.models;

public class RemoveDataObjectToMachineCodeModel extends ProductKeyMachineCodeModel {
    /**
     * The unique id of the data object to be removed.
     */
    public long Id;

    public RemoveDataObjectToMachineCodeModel(int productId, String key, String machineCode, long id) {
        Id = id;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
    }
}
