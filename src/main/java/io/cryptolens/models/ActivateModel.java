package io.cryptolens.models;

/**
 * The parameters that are used when calling the Key.Activate method.
 */
public class ActivateModel {

    /**
     * The product id, which can be found on the product page.
     */
    public int ProductId;

    /**
     * The Key string, eg. AAAA-BBBB-CCCC-DDDD.
     */
    public String Key;

    /**
     * The machine code (a string that identifies a device) for activation.
     */
    public String MachineCode;

    /**
     * An integer that allows you to restrict the information returned in the license key data object.
     * Please read https://app.cryptolens.io/docs/api/v3/Activate#remarks for more details.
     */
    public int FieldsToReturn;

    /**
     * Includes additional information about the license key, such as number of activated devices, etc.
     */
    public boolean Metadata;

    /**
     * When set to something greater than zero, floating licensing will be enabled.
     * The time interval is then used to check that no more than the allowed number
     * of machine codes (specified in maximumNumberOfMachines) have been activated
     * in that time period (in seconds).
     */
    public int FloatingTimeInterval;

    /**
     * When set to something greater than zero (and assuming FloatingTimeInterval is set too)
     * floating licensing will permit a license overdraft (eg. activation will succeed even if
     * maximumNumberOfMachines is reached). MaxOverdraft value specifies how much we can exceed
     * the maximumNumberOfMachines value.
     */
    public int MaxOverdraft;

    /**
     * Allows you to specify a friendy name for the activated device, for example the employee's email.
     * Friendly name does not impact the number of active machine codes / seats, but it offers an easy way
     * of linking a machine/seat with a user. For added security, you can HMAC hash this value.
     * <br/>
     * The computer name can, as an example, be used as the friendly name:
     * <code>
     *     String friendlyName = InetAddress.getLocalHost().getHostName();
     * </code>
     *
     */
    public String FriendlyName;

    public ActivateModel() {

    }
    public ActivateModel(int productId, String key, String machineCode) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
    }

    public ActivateModel(int productId, String key, String machineCode, String friendlyName) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FriendlyName = friendlyName;
    }

    public ActivateModel(int productId, String key, String machineCode, int floatingTimeInterval) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FloatingTimeInterval = floatingTimeInterval;
    }

    public ActivateModel(int productId, String key, String machineCode, int floatingTimeInterval, String friendlyName) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FloatingTimeInterval = floatingTimeInterval;
        FriendlyName = friendlyName;
    }


    public ActivateModel(int productId, String key, String machineCode, int floatingTimeInterval, int maxOverdraft) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FloatingTimeInterval = floatingTimeInterval;
        MaxOverdraft = maxOverdraft;
    }

    public ActivateModel(int productId, String key, String machineCode, int floatingTimeInterval, int maxOverdraft, String friendlyName) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FloatingTimeInterval = floatingTimeInterval;
        MaxOverdraft = maxOverdraft;
        FriendlyName = friendlyName;
    }

    public ActivateModel(int productId, String key, String machineCode, int fieldsToReturn, boolean metadata, int floatingTimeInterval, int maxOverdraft) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FieldsToReturn = fieldsToReturn;
        Metadata = metadata;
        FloatingTimeInterval = floatingTimeInterval;
        MaxOverdraft = maxOverdraft;
    }

    public ActivateModel(int productId, String key, String machineCode, int fieldsToReturn, boolean metadata, int floatingTimeInterval, int maxOverdraft, String friendlyName) {
        ProductId = productId;
        Key = key;
        MachineCode = machineCode;
        FieldsToReturn = fieldsToReturn;
        Metadata = metadata;
        FloatingTimeInterval = floatingTimeInterval;
        MaxOverdraft = maxOverdraft;
        FriendlyName = friendlyName;
    }
}
