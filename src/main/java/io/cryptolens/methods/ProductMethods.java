package io.cryptolens.methods;

import io.cryptolens.internal.*;
import io.cryptolens.models.GetProductsResult;
import io.cryptolens.models.RequestModel;

import java.util.*;

/**
 * Methods that perform operations on a product. A complete list
 * can be found <a href="https://app.cryptolens.io/docs/api/v3/Product">here</a>.
 */
public class ProductMethods {

    /**
     * This method will return the list of products.
     * @param token An access token with "GetProducts" permission.
     * @return GetProductsResult object if successful and null otherwise.
     */
    public static GetProductsResult GetProducts (String token) {
        Map<String, String> extraParams = new HashMap<>();
        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("product/GetProducts", new RequestModel(), extraParams, GetProductsResult.class);
    }
}
