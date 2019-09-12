package io.cryptolens.methods;

import io.cryptolens.internal.BasicResult;
import io.cryptolens.internal.HelperMethods;
import io.cryptolens.models.*;
import io.cryptolens.internal.ActivateResult;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>A collection of methods that operate on a license key. Please see a complete
 * list <a href="https://app.cryptolens.io/docs/api/v3/Key">here</a>.</p>
 *
 * <p><b>Note (for Android)</b>: it's important to run these methods asynchronously, as shown below:</p>
 * <pre>
 * Thread thread = new Thread(new Runnable() {
 *     &#64;Override
 *     public void run() {
 *         // call eg. Key.Activate(...).
 *     }
 * });
 * thread.start();
 * </pre>
 *
 * <p>Moreover, the manifest file needs to have internet permission, which can be added as shown below:</p>
 * <pre>
 * &lt;uses-permission android:name="android.permission.INTERNET"/&gt;
 *
 * &lt;application ....
 * </pre>
 *
 */
public class Key {

    /**
     * Calls the Activate method (https://app.cryptolens.io/docs/api/v3/Activate).
     * @param token The access token with 'Activate' permission.
     * @param RSAPubKey Your RSA Public Key, which can be found at https://app.cryptolens.io/docs/api/v3/QuickStart.
     * @param model Method parameters.
     * @return A LicenseKey object if success and null otherwise.
     */
    public static LicenseKey Activate (String token, String RSAPubKey, ActivateModel model) {
        return Activate(token, RSAPubKey, model,null);
    }

    /**
     * Calls the Activate method (https://app.cryptolens.io/docs/api/v3/Activate).
     * <p>This method allows you to retrieve the error message from the Web API.</p>
     * <p>
     *     To retrieve the error message, you need to initialize an APIError object and pass it in into the
     *     "error" parameter. For example,
     * </p>
     * <code>
     *     APIError error = new APIError();<br>
     *     LicenseKey license = Key.Activate(auth, RSAPubKey, new ActivateModel(3349, "ICVLD-VVSZR-ZTICT-YKGXL", Helpers.GetMachineCode()), error);<br>
     *     System.out.println(error.Message);
     * </code>
     * @param token The access token with 'Activate' permission.
     * @param RSAPubKey Your RSA Public Key, which can be found at https://app.cryptolens.io/docs/api/v3/QuickStart.
     * @param model Method parameters.
     * @param error The error object whose Message field will be populated if an error has occurred. Please initialize
     *              this parameter, i.e. define <code>APIError error = new APIError();</code> and then pass
     *              <code>error</code> into this parameter.
     * @return A LicenseKey object if success and null otherwise.
     */
    public static LicenseKey Activate (String token, String RSAPubKey, ActivateModel model, APIError error) {

        Map<String,String> extraParams = new HashMap<>();

        // force sign and the new protocol (only in activate)
        extraParams.put("Sign", "true");
        extraParams.put("SignMethod", "1");
        extraParams.put("token", token);

        ActivateResult result = HelperMethods.SendRequestToWebAPI("key/activate", model, extraParams, ActivateResult.class);

        if(result == null || result.result == 1) {
            if(result != null) {
                if (error != null) {
                    error.Message = result.message;
                }
                System.err.println("The server returned an error: " + result.message);
            } else {
                System.err.println("The server returned an error.");
            }
            return null;
        }

        return LicenseKey.LoadFromString(RSAPubKey, result.RawResponse);
    }

    /**
     * This method will 'undo' a key activation with a certain machine code.
     * The key should not be blocked, since otherwise this method will throw an error.
     * More info: https://app.cryptolens.io/docs/api/v3/Deactivate
     * @param token The access token with 'Deactivate' permission.
     * @param model Method parameters.
     * @return True if deactivation succeeded and false otherwise.
     */
    public static boolean Deactivate (String token, DeactivateModel model) {

        Map<String,String> extraParams = new HashMap<>();
        extraParams.put("token", token);

        BasicResult result = HelperMethods.SendRequestToWebAPI("key/deactivate", model, extraParams, BasicResult.class);

        if(result == null || result.result == 1) {
            if(result != null) {
                System.err.println("The server returned an error: " + result.message);
            } else {
                System.err.println("The server returned an error.");
            }
            return false;
        }

        return true;
    }

    /**
     * <p>This method creates a license key that is time-limited, <a href="https://help.cryptolens.io/licensing-models/node-locked" target="_blank">node-locked</a>
     * and with the "Time-Limited" and "Trial" features set to true (which can be set by editing the
     * <a href="https://help.cryptolens.io/web-interface/feature-definitions" target="_blank">feature definitions</a> on the product page).
     * Note, by default, the trial will work for 15 days. To change this limit, you can set the <b>Feature Lock</b>
     * to the desired value, when creating the access token.</p>
     *
     * <p>If a trial key was already created for a certain machine code, this method will try to find the license
     * key and return it instead. However, this will only occur if the license key is still a trial key (based
     * on feature definitions) and is not blocked.</p>
     * @param token The access token with 'CreateTrialKey' permission.
     * @param model Method parameters.
     * @return A new license key or error message.
     */
    public static CreateKeyResult CreateTrialKey(String token, CreateTrialKeyModel model) {
        Map<String,String> extraParams = new HashMap<>();
        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("key/createtrialkey", model, extraParams, CreateKeyResult.class);
    }
}
