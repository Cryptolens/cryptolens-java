package io.cryptolens.methods;

import com.google.gson.Gson;
import io.cryptolens.internal.*;
import io.cryptolens.models.*;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Data {
    /**
     * Adds a new data object to a license key.
     * @param token
     * @param license
     * @param name
     * @param intValue
     * @param stringValue
     * @return
     */
    public static BasicResult AddDataObject(String token, LicenseKey license, String name, int intValue, String stringValue) {
        return AddDataObject(token, new AddDataObjectToKeyModel(license.ProductId, license.Key, name, intValue, stringValue));
    }

    public static BasicResult AddDataObject(String token, AddDataObjectToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/AddDataObjectToKey", model, extraParams, BasicResult.class);
    }

    public static ListOfDataObjectsResult ListDataObjects(String token, LicenseKey license) {
        return ListDataObjects(token, new ListDataObjectsToKeyModel(license.ProductId, license.Key, ""));
    }

    public static ListOfDataObjectsResult ListDataObjects(String token, LicenseKey license, String contains) {
        return ListDataObjects(token, new ListDataObjectsToKeyModel(license.ProductId, license.Key, contains));
    }

    public static ListOfDataObjectsResult ListDataObjects(String token, ListDataObjectsToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/listdataobjectstokey", model, extraParams, ListOfDataObjectsResult.class);
    }

    public static BasicResult SetIntValue(String token, LicenseKey license, long id, int intValue) {
        return SetIntValue(token, new SetIntValueToKeyModel(license.ProductId, license.Key, id, intValue));
    }

    public static BasicResult SetIntValue(String token, SetIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult IncrementIntValue(String token, LicenseKey license, long id, int intValue) {
        return IncrementIntValue(token, new IncrementIntValueToKeyModel(license.ProductId, license.Key, id, intValue));
    }

    public static BasicResult IncrementIntValue(String token, IncrementIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/incrementintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult DecrementIntValue(String token, LicenseKey license, long id, int intValue) {
        return DecrementIntValue(token, new DecrementIntValueToKeyModel(license.ProductId, license.Key, id, intValue));
    }

    public static BasicResult DecrementIntValue(String token, DecrementIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/decrementintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult SetStringValue(String token, LicenseKey license, long id, String intValue) {
        return SetStringValue(token, new SetStringValueToKeyModel(license.ProductId, license.Key, id, intValue));
    }

    public static BasicResult SetStringValue(String token, SetStringValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setstringvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult RemoveDataObject(String token, LicenseKey license, long id) {
        return RemoveDataObject(token, new RemoveDataObjectToKeyModel(license.ProductId, license.Key, id));
    }

    public static BasicResult RemoveDataObject(String token, RemoveDataObjectToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/removedataobjecttokey", model, extraParams, BasicResult.class);
    }
}
