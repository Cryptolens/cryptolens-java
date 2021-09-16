package io.cryptolens.methods;

import io.cryptolens.internal.BasicResult;
import io.cryptolens.internal.HelperMethods;
import io.cryptolens.models.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Methods related to the Message API (https://app.cryptolens.io/docs/api/v3/Message).
 * You can broadcast new messages by visiting https://app.cryptolens.io/Message.
 */
public class Message {

    // create an overload without the error object

    /**
     * This method will return a list of messages that were broadcasted.
     * You can create new messages here. Messages can be filtered based
     * on the time and the channel.
     * @param token The access token with 'GetMessages' permission.
     * @param model Method parameters.
     */
    public static GetMessagesResult GetMessages (String token, GetMessagesModel model) {
        return GetMessages(token, model, null);
    }


    /**
     * This method will return a list of messages that were broadcasted.
     * You can create new messages here. Messages can be filtered based
     * on the time and the channel.
     * @param token The access token with 'GetMessages' permission.
     * @param model Method parameters.
     * @param error The error object whose Message field will be populated if an error has occurred. Please initialize
     *              this parameter, i.e. define <code>APIError error = new APIError();</code> and then pass
     *              <code>error</code> into this parameter.
     */
    public static GetMessagesResult GetMessages (String token, GetMessagesModel model, APIError error) {


        Map<String,String> extraParams = new HashMap<>();
        extraParams.put("token", token);

        GetMessagesResult result = HelperMethods.SendRequestToWebAPI("message/GetMessages", model, extraParams, GetMessagesResult.class, error);

        if(result == null || result.result == 1) {
            if(result != null) {
                if (error != null) {
                    error.message = result.message;
                    error.errorType = ErrorType.WebAPIError;
                }
            } else {
                if (error != null) {
                    error.errorType = ErrorType.WebAPIError;
                }
            }
        }

        return result;
    }

}
