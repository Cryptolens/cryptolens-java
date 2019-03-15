package io.cryptolens.models;

public class RemoveDataObjectToMachineCodeModel extends ProductKeyMachineCodeModel {
    /**
     * The unique id of the data object to be removed.
     */
    public long Id;

    public String Name;

    public RemoveDataObjectToMachineCodeModel(int productId, String key, String machineCode, long id) {
        Id = id;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
        this.Name = "";
    }

    public RemoveDataObjectToMachineCodeModel(int productId, String key, String machineCode, String name) {
        Id = 0;
        this.ProductId = productId;
        this.Key = key;
        this.MachineCode = machineCode;
        this.Name = name;
    }
}
