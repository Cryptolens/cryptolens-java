package io.cryptolens.models;

public class RegisterEventModel extends ProductKeyMachineCodeModel {

    public String FeatureName;
    public String EventName;
    public int Value;
    public String Currency;

    public RegisterEventModel(int productId, String key, String machineCode, String featureName, String eventName, int value, String currency) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FeatureName = featureName;
        EventName = eventName;
        Value = value;
        Currency = currency;
    }
}
