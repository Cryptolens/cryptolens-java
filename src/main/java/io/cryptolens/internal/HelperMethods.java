package io.cryptolens.internal;

import com.google.gson.*;
import io.cryptolens.legacy.HttpsURLConnectionRequestHandler;
import io.cryptolens.legacy.RequestHandler;
import io.cryptolens.models.APIError;
import io.cryptolens.models.ErrorType;
import io.cryptolens.models.RequestModel;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HelperMethods {

    public static <T extends BasicResult> T SendRequestToWebAPI(String method, RequestModel model, Map<String,String> extraParams, Class<T> clazz) {
        return SendRequestToWebAPI(method, model, extraParams, clazz, null);
    }

    public static <T extends BasicResult> T SendRequestToWebAPI(String method, RequestModel model, Map<String,String> extraParams, Class<T> clazz, APIError error) {

        Map<String,String> params = new HashMap<>();
        List<Field> allFields = new ArrayList<>();
        getAllFields(allFields, model.getClass());

        String licenseServerUrl = "https://app.cryptolens.io";

        for(Field field : allFields) {
            field.setAccessible(true);
            try {
                Object value = field.get(model);
                if(value != null) {

                    if(field.getName() == "LicenseServerUrl") {
                        licenseServerUrl = field.toString();
                    } else {
                        params.put(field.getName(), value.toString());
                    }
                }
            } catch (Exception ex) {
                if(error != null) {
                    error.errorType = ErrorType.LibraryError;
                    error.message = ex.getMessage();
                }
            }
        }

        if(extraParams != null)
            params.putAll(extraParams);

        RequestHandler requestHandler = new HttpsURLConnectionRequestHandler();

        try {

            String response = requestHandler.makePostRequest(licenseServerUrl + "/api/" + method, params);

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    /*Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
                    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()*/
                    return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                }
            }).create();

            T res = gson.fromJson(response, clazz);
            res.RawResponse = response;
            return res;

        } catch (Exception ex) {
            if(error != null) {
                error.errorType = ErrorType.LibraryError;
                error.message = ex.toString();
            }
        }

        return  null;
    }

    // from: https://stackoverflow.com/a/1042827/1275924
    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
