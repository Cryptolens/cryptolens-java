package io.cryptolens.methods;

import io.cryptolens.internal.*;
import io.cryptolens.models.*;

import java.util.HashMap;
import java.util.Map;

public class Data {

    // add additional methods so that one can pass in license key object.

    public static BasicResult AddDataObject(String token, AddDataObjectToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/AddDataObjectToKey", model, extraParams, BasicResult.class);
    }

    public static ListOfDataObjectsResult ListDataObjects(String token, ListDataObjectsToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/listdataobjectstokey", model, extraParams, ListOfDataObjectsResult.class);
    }


    public static BasicResult SetIntValue(String token, SetIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult IncrementIntValue(String token, IncrementIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/incrementintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult DecrementIntValue(String token, DecrementIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/decrementintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult SetStringValue(String token, SetStringValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setstringvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult RemoveDataObject(String token, RemoveDataObjectToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/removedataobjecttokey", model, extraParams, BasicResult.class);
    }



}
