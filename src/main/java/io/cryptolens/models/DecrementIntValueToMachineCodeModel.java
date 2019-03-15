package io.cryptolens.models;

public class DecrementIntValueToMachineCodeModel extends ProductKeyMachineCodeModel {

    /**
     * The unique object id for the data object.
     */
    public long Id;

    public String Name;

    /**
     * 	The constant int value that should be subtracted to the current IntValue of the data object. For example,
     * 	if this value is set to 5 and the old IntValue is 11, then the new IntValue will be the old one minus 5, i.e. 6.
     * 	Note, if you would set this value to -5 instead, the same result would be achieved.
     */
    public int IntValue;

    /**
     * If set to true, it will be possible to specify a lower bound. For example, if you set the Bound parameter
     * (below) to 0, you will be able to decrement the int value until you reach zero (inclusive).
     * Once the lower bound is reached, an error will be thrown.
     */
    public boolean EnableBound;

    /**
     * 	This is the lower bound that will be enforced on the decrement operation.
     * 	It will only be enforced if EnableBound is set to true. Please read the description above.
     */
    public int Bound;

    public DecrementIntValueToMachineCodeModel(int productId, String key, String machineCode, long id, int intValue, boolean enableBound, int bound) {
        Id = id;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
        this.MachineCode = machineCode;
        this.Name = "";
    }

    public DecrementIntValueToMachineCodeModel(int productId, String key, String machineCode, String name, int intValue, boolean enableBound, int bound) {
        Id = 0;
        IntValue = intValue;
        this.ProductId = productId;
        this.Key = key;
        this.EnableBound = enableBound;
        this.Bound = bound;
        this.MachineCode = machineCode;
        this.Name = name;
    }

}
