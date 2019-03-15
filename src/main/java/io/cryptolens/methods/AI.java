package io.cryptolens.methods;

import io.cryptolens.internal.BasicResult;
import io.cryptolens.internal.HelperMethods;
import io.cryptolens.models.RegisterEventModel;

import java.util.HashMap;
import java.util.Map;

public class AI {

    public static BasicResult RegisterEvent(String token, RegisterEventModel model) {
        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("AI/RegisterEvent", model, extraParams, BasicResult.class);
    }
}
