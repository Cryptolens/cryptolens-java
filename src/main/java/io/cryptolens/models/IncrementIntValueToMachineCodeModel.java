package io.cryptolens.models;

public class IncrementIntValueToMachineCodeModel extends ProductKeyMachineCodeModel {

    /**
     * 	The unique object id for the data object.
     */
    public long Id;

    public String Name;
    /**
     * The constant int (non-negative) value that should be added to the current IntValue of the data object.
     * For example, if this value is set to 5 and the old IntValue is 1, then the new IntValue will be the
     * old one plus 5, i.e. 6. Note, if you would set this value to -5 instead, the same result would be achieved.
     */
    public int IntValue;
    /**
     * If set to true, it will be possible to specify an upper bound. For example, if you set the Bound parameter
     * (below) to 10, you will be able to increment the int value until you reach ten (inclusive).
     * Once the upper bound is reached, an error will be thrown.
     */
    public boolean EnableBound;

    /**
     * This is the upper bound that will be enforced on the increment operation.
     * It will only be enforced if EnableBound is set to true. Please read the description above.
     */
    public int Bound;

    public IncrementIntValueToMachineCodeModel(int productId, String key, String machineCode, long id, int intValue, boolean enableBound, int bound) {
        Id = id;
        this.IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
        this.MachineCode = machineCode;
        this.Name = "";
    }
    public IncrementIntValueToMachineCodeModel(int productId, String key, String machineCode, String name, int intValue, boolean enableBound, int bound) {
        this.IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
        this.MachineCode = machineCode;
        this.Name = name;
        Id = 0;
    }
}
