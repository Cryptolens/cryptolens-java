package io.cryptolens.models;

/**
 * The parameters that are used when calling the Key.GetKey method.
 */

public class GetKeyModel {
    /**
     * The product id, which can be found on the product page.
     */
    public int ProductId;

    /**
     * The Key string, eg. AAAA-BBBB-CCCC-DDDD.
     */
    public String Key;

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
     * Allows you specify the floatingTimeInterval. The current version of the SDK uses a modelVersion=3,
     * which means that all activated devices (node-locked and floating) will be returned in ActivatedMachines,
     * independent of the floatingTimeInterval value. This value is only taken into account in the additional
     * metadata that is returned (if Metadata=true). In most use cases, this parameter does not need to be used.
     */
    public int FloatingTimeInterval;

    public GetKeyModel() {

    }

    public GetKeyModel(int productId, String key) {
        ProductId = productId;
        Key = key;
    }

    public GetKeyModel(int productId, String key, int floatingTimeInterval, boolean metadata) {
        ProductId = productId;
        Key = key;
        FloatingTimeInterval = floatingTimeInterval;
        Metadata = metadata;
    }
}
