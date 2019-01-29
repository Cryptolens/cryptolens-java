package io.cryptolens.models;

public class ActivateModel {

    /// <summary>
    /// The product id, which can be found on the product page.
    /// </summary>
    public int ProductId;

    /// <summary>
    /// The Key string, eg. AAAA-BBBB-CCCC-DDDD.
    /// </summary>
    public String Key;

    /// <summary>
    /// The machine code (a string that identifies a device) for activation.
    /// </summary>
    public String MachineCode;

    /// <summary>
    /// An integer that allows you to restrict the information returned in the license key data object.
    /// Please read https://app.cryptolens.io/docs/api/v3/Activate#remarks for more details.
    /// </summary>
    public int FieldsToReturn;

    /// <summary>
    /// Includes additional information about the license key, such as number of activated devices, etc.
    /// </summary>
    public boolean Metadata;

    /// <summary>
    /// When set to something greater than zero, floating licensing will be enabled.
    /// The time interval is then used to check that no more than the allowed number
    /// of machine codes (specified in maximumNumberOfMachines) have been activated
    /// in that time period (in seconds).
    /// </summary>
    public int FloatingTimeInterval;

    /// <summary>
    /// When set to something greater than zero (and assuming FloatingTimeInterval is set too)
    /// floating licensing will permit a license overdraft (eg. activation will succeed even if
    /// maximumNumberOfMachines is reached). MaxOverdraft value specifies how much we can exceed
    /// the maximumNumberOfMachines value.
    /// </summary>
    public int MaxOverdraft;

}
