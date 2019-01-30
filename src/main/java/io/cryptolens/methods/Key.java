package io.cryptolens.methods;

import io.cryptolens.GsonResponseParser;
import io.cryptolens.HttpsURLConnectionRequestHandler;
import io.cryptolens.JavaSecuritySignatureVerifier;
import io.cryptolens.RequestHandler;
import io.cryptolens.internal.HelperMethods;
import io.cryptolens.models.ActivateModel;
import io.cryptolens.models.BasicResult;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Key {

    public static BasicResult Activate (String token, ActivateModel model) throws Exception {

        Map<String,String> extraParams = new HashMap<>();

        // force sign and the new protocol (only in activate)
        extraParams.put("Sign", "true");
        extraParams.put("SignMethod", "1");
        extraParams.put("token", token);

        BasicResult result = HelperMethods.SendRequestToWebAPI("key/activate", model, extraParams);

        JavaSecuritySignatureVerifier signatureVerifier = new JavaSecuritySignatureVerifier();
        GsonResponseParser responseParser = new GsonResponseParser();

        return result;


    }

}
