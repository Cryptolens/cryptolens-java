package io.cryptolens.models;

public class ExtendLicenseModel extends RequestModel {

    /**
     * The product id.
     */
    int ProductId;

    /**
     * The license key string.
     */
    String Key;

    /**
     * The number of days the license should be extended.
     */
    int NoOfDays;

    public ExtendLicenseModel(int productId, String key, int noOfDays) {
        ProductId = productId;
        Key = key;
        NoOfDays = noOfDays;
    }
}
