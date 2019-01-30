package io.cryptolens.internal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cryptolens.*;
import io.cryptolens.models.BasicResult;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class HelperMethods {

    public static <T extends BasicResult> T SendRequestToWebAPI(String method, Object model, Map<String,String> extraParams) throws Exception {

        Map<String,String> params = new HashMap<>();

        for(Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(model);
                if(value != null) {
                    params.put(field.getName(), value.toString());
                }
            }
            catch (Exception ex) {}
        }

        if(extraParams != null)
            params.putAll(extraParams);

        RequestHandler requestHandler = new HttpsURLConnectionRequestHandler();



            String response = requestHandler.makePostRequest("https://app.cryptolens.io/api/" + method, params);

            Gson gson = new Gson();

            Type typeOfT = new TypeToken<T>(){}.getType();
            return gson.fromJson(response, typeOfT);

            /*Base64Response base64response = responseParser.parseBase64Response(response);

            if (base64response.message != null) {
                return new Cryptolens.ActivateResponse(parseServerMessage(base64response.message));
            }

            if (!signatureVerifier.verify(base64response.licenseKey, base64response.signature)) {
                System.err.println("Signature check failed");
                return new Cryptolens.ActivateResponse(new RuntimeException("Invalid signature"));
            }
            return new Cryptolens.ActivateResponse(responseParser.parseLicenseKey(base64response.licenseKey));*/


        //return  null;
    }

    private static Cryptolens.ActivateServerError parseServerMessage(String message) {
        if ("Unable to authenticate.".equals(message)) {
            return Cryptolens.ActivateServerError.INVALID_ACCESS_TOKEN;
        }

        if ("Access denied.".equals(message)) {
            return Cryptolens.ActivateServerError.ACCESS_DENIED;
        }

        if ("The input parameters were incorrect.".equals(message)) {
            return Cryptolens.ActivateServerError.INCORRECT_INPUT_PARAMETER;
        }

        if ("Could not find the product.".equals(message)) {
            return Cryptolens.ActivateServerError.PRODUCT_NOT_FOUND;
        }

        if ("Could not find the key.".equals(message)) {
            return Cryptolens.ActivateServerError.KEY_NOT_FOUND;
        }

        if ("The key is blocked and cannot be accessed.".equals(message)) {
            return Cryptolens.ActivateServerError.KEY_BLOCKED;
        }

        if ("Cannot activate the new device as the limit has been reached.".equals(message)) {
            return Cryptolens.ActivateServerError.DEVICE_LIMIT_REACHED;
        }

        return Cryptolens.ActivateServerError.UNKNOWN_SERVER_REPLY;
    }
}
