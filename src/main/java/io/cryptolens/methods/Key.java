package io.cryptolens.methods;

import com.google.gson.Gson;
import io.cryptolens.*;
import io.cryptolens.internal.HelperMethods;
import io.cryptolens.models.ActivateModel;
import io.cryptolens.models.ActivateResult;
import io.cryptolens.models.BasicResult;
import io.cryptolens.models.LicenseKey;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

}
