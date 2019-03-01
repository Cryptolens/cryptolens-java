package io.cryptolens.methods;

import io.cryptolens.internal.*;
import io.cryptolens.models.*;

import java.util.HashMap;
import java.util.Map;

public class Data {

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


    public static ListOfDataObjectsResult SetIntValue(String token, ChangeIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/listdataobjectstokey", model, extraParams, ListOfDataObjectsResult.class);
    }


}