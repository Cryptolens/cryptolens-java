package io.cryptolens.methods;

import io.cryptolens.internal.HelperMethods;
import io.cryptolens.models.ActivateModel;
import io.cryptolens.internal.ActivateResult;
import io.cryptolens.models.LicenseKey;

import java.util.HashMap;
import java.util.Map;

/**
 * A collection of methods that operate on a license key. Please see a complete
 * list here: https://app.cryptolens.io/docs/api/v3/Key
 */
public class Key {

    /**
     * Calls the Activate method (https://app.cryptolens.io/docs/api/v3/Activate).
     * @param token
     * @param RSAPubKey
     * @param model
     * @return
     */
    public static LicenseKey Activate (String token, String RSAPubKey, ActivateModel model) {

        Map<String,String> extraParams = new HashMap<>();

        // force sign and the new protocol (only in activate)
        extraParams.put("Sign", "true");
        extraParams.put("SignMethod", "1");
        extraParams.put("token", token);

        ActivateResult result = HelperMethods.SendRequestToWebAPI("key/activate", model, extraParams, ActivateResult.class);

        if(result.result == 1) {
            System.err.println("The server returned an error: " + result.message);
            return null;
        }

        return LicenseKey.LoadFromString(RSAPubKey, result.RawResponse);
    }

    public static boolean Deactivate (String token) {
        return false;
    }

}
