package io.cryptolens.methods;

import io.cryptolens.internal.BasicResult;
import io.cryptolens.internal.HelperMethods;
import io.cryptolens.models.RecordUsageModel;

import java.util.HashMap;
import java.util.Map;

/**
 * These methods are related to the <a href="https://help.cryptolens.io/recurring-payments/index">recurring billing</a> module.
 * They can all be accessed using an access token with 'Subscription' permission.
 */
public class Subscription {
    /**
     * This method records uses Stripe's metered billing to record usage for a certain subscription.
     * In order to use this mehtod, you need to have set up recurring billing. A record will be created using Stripe's API with action set to 'increment'.
     * More info: https://app.cryptolens.io/docs/api/v3/RecordUsage
     * @param token An access token with "GetProducts" permission.
     * @return BasicResult object. Null can be returned if an error occurs.
     */
    public static BasicResult RecordUsage (String token, RecordUsageModel model) {
        Map<String, String> extraParams = new HashMap<>();
        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("subscription/recordusage/", model, extraParams, BasicResult.class);
    }
}
