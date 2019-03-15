package io.cryptolens.methods;

import com.google.gson.Gson;
import io.cryptolens.internal.*;
import io.cryptolens.models.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>The following methods allow you to work with data objects (aka. metadata or custom variables) associated with a
 * license key or a machine code (i.e. an activation). Data objects can be used to store specific properties (eg. username, OS version). More importantly
 * though, they are used if you plan to implement a <a href="https://help.cryptolens.io/licensing-models/usage-based" target="_blank">usage-based licensing model</a>.
 * </p>
 *
 * <p><b>Access token remarks:</b> When you create an access token for any of the methods below, we recommend to <u>specify the product</u>
 * and to set the <u>keylock to <b>-1</b></u>.</p>
 *
 * <p><b>Remarks for machine code data objects:</b> In order to work with data objects associated with a machine code, the machine code needs
 * to be assigned to a license key. After deactivation, it will not be possible to retrieve the data objects using the methods below (it is,
 * however, possible to retrieve them using the Web API).</p>
 */
public class Data {
    /**
     * Adds a new data object to a license key.
     * @param token The access token with 'AddDataObject' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object. Max 10 characters.
     * @param intValue 	An int value (int32) to store.
     * @param stringValue A string value (text) to store. Max 10000 characters.
     * @return
     */
    public static BasicResult AddDataObject(String token, LicenseKey license, String name, int intValue, String stringValue) {
        return AddDataObject(token, new AddDataObjectToKeyModel(license.ProductId, license.Key, name, intValue, stringValue));
    }

    /**
     * Adds a new data object to an existing activation (machine code).
     * @param token The access token with 'AddDataObject' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object. Max 10 characters.
     * @param intValue 	An int value (int32) to store.
     * @param stringValue A string value (text) to store. Max 10000 characters.
     * @return
     */
    public static BasicResult AddDataObject(String token, LicenseKey license, String machineCode, String name, int intValue, String stringValue) {
        return AddDataObject(token, new AddDataObjectToMachineCodeModel(license.ProductId, license.Key, machineCode, name, intValue, stringValue));
    }


    public static BasicResult AddDataObject(String token, AddDataObjectToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/AddDataObjectToKey", model, extraParams, BasicResult.class);
    }

    public static BasicResult AddDataObject(String token, AddDataObjectToMachineCodeModel model) {

        Map<String, String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/AddDataObjectToMachineCode", model, extraParams, BasicResult.class);
    }

    /**
     * List data objects of a certain license.
     * @param token The access token with 'ListDataObjects' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param contains Shows only Data Objects where the name contains the following string.
     * @return
     */
    public static ListOfDataObjectsResult ListDataObjects(String token, LicenseKey license, String contains) {
        return ListDataObjects(token, new ListDataObjectsToKeyModel(license.ProductId, license.Key, contains));
    }

    /**
     * List data objects of an existing activation (machine code).
     * @param token The access token with 'ListDataObjects' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param contains Shows only Data Objects where the name contains the following string.
     * @return
     */
    public static ListOfDataObjectsResult ListDataObjects(String token, LicenseKey license, String machineCode, String contains) {
        return ListDataObjects(token, new ListDataObjectsToMachineCodeModel(license.ProductId, license.Key, machineCode , contains));
    }

    public static ListOfDataObjectsResult ListDataObjects(String token, ListDataObjectsToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/listdataobjectstokey", model, extraParams, ListOfDataObjectsResult.class);
    }

    public static ListOfDataObjectsResult ListDataObjects(String token, ListDataObjectsToMachineCodeModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/listdataobjectstomachinecode", model, extraParams, ListOfDataObjectsResult.class);
    }

    /**
     * This method will assign a new integer value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param id The unique object id for the data object.
     * @param intValue The new int value that should be assigned to the data object.
     * @return
     */
    public static BasicResult SetIntValue(String token, LicenseKey license, long id, int intValue) {
        return SetIntValue(token, new SetIntValueToKeyModel(license.ProductId, license.Key, id, intValue));
    }

    /**
     * This method will assign a new integer value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object.
     * @param intValue The new int value that should be assigned to the data object.
     * @return
     */
    public static BasicResult SetIntValue(String token, LicenseKey license, String name, int intValue) {
        return SetIntValue(token, new SetIntValueToKeyModel(license.ProductId, license.Key, name, intValue));
    }

    /**
     * This method will assign a new integer value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param id The unique object id for the data object.
     * @param intValue The new int value that should be assigned to the data object.
     * @return
     */
    public static BasicResult SetIntValue(String token, LicenseKey license, String machineCode, long id, int intValue) {
        return SetIntValue(token, new SetIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, id, intValue));
    }

    /**
     * This method will assign a new integer value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object.
     * @param intValue The new int value that should be assigned to the data object.
     * @return
     */
    public static BasicResult SetIntValue(String token, LicenseKey license, String machineCode, String name, int intValue) {
        return SetIntValue(token, new SetIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, name, intValue));
    }

    public static BasicResult SetIntValue(String token, SetIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult SetIntValue(String token, SetIntValueToMachineCodeModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setintvaluetomachinecode", model, extraParams, BasicResult.class);
    }

    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, long id, int intValue) {
        return IncrementIntValue(token, new IncrementIntValueToKeyModel(license.ProductId, license.Key, id, intValue, false, 0));
    }

    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, String name, int intValue) {
        return IncrementIntValue(token, new IncrementIntValueToKeyModel(license.ProductId, license.Key, name, intValue, false, 0));
    }


    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, String machineCode, long id, int intValue) {
        return IncrementIntValue(token, new IncrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, id, intValue, false, 0));
    }

    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, String machineCode, String name, int intValue) {
        return IncrementIntValue(token, new IncrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, name, intValue, false, 0));
    }

    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @param enableBound 	If set to true, it will be possible to specify an upper bound. For example,
     *                      if you set the Bound parameter (below) to 10, you will be able to increment
     *                      the int value until you reach ten (inclusive). Once the upper bound is reached,
     *                      an error will be thrown.
     * @param bound 	This is the upper bound that will be enforced on the increment operation.
     *                  It will only be enforced if EnableBound is set to true. Please read the description about enableBound.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, long id, int intValue, boolean enableBound, int bound) {
        return IncrementIntValue(token, new IncrementIntValueToKeyModel(license.ProductId, license.Key, id, intValue, enableBound, bound));
    }

    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @param enableBound 	If set to true, it will be possible to specify an upper bound. For example,
     *                      if you set the Bound parameter (below) to 10, you will be able to increment
     *                      the int value until you reach ten (inclusive). Once the upper bound is reached,
     *                      an error will be thrown.
     * @param bound 	This is the upper bound that will be enforced on the increment operation.
     *                  It will only be enforced if EnableBound is set to true. Please read the description about enableBound.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, String name, int intValue, boolean enableBound, int bound) {
        return IncrementIntValue(token, new IncrementIntValueToKeyModel(license.ProductId, license.Key, name, intValue, enableBound, bound));
    }

    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @param enableBound 	If set to true, it will be possible to specify an upper bound. For example,
     *                      if you set the Bound parameter (below) to 10, you will be able to increment
     *                      the int value until you reach ten (inclusive). Once the upper bound is reached,
     *                      an error will be thrown.
     * @param bound 	This is the upper bound that will be enforced on the increment operation.
     *                  It will only be enforced if EnableBound is set to true. Please read the description about enableBound.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, String machineCode, long id, int intValue, boolean enableBound, int bound) {
        return IncrementIntValue(token, new IncrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, id, intValue, enableBound, bound));
    }

    /**
     * This method will increment the integer value in a Data Object by a certain constant (non-negative).
     * You can always decrement it. Note, this method does not allow integer overflows, i.e. if you increment
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the Feature lock
     * in the Access Token to specify the upper bound of the increment constant. So, if you only want to allow incrementing
     * by 1, please set Feature lock field to 1 also. Please see Remarks for more details (including access token set up).
     * @param token The access token with 'IncrementIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object.
     * @param intValue 	The constant int (non-negative) value that should be added to the current
     *                  IntValue of the data object. For example, if this value is set to 5 and the
     *                  old IntValue is 1, then the new IntValue will be the old one plus 5, i.e. 6.
     *                  Note, if you would set this value to -5 instead, the same result would be achieved.
     * @param enableBound 	If set to true, it will be possible to specify an upper bound. For example,
     *                      if you set the Bound parameter (below) to 10, you will be able to increment
     *                      the int value until you reach ten (inclusive). Once the upper bound is reached,
     *                      an error will be thrown.
     * @param bound 	This is the upper bound that will be enforced on the increment operation.
     *                  It will only be enforced if EnableBound is set to true. Please read the description about enableBound.
     * @return
     */
    public static BasicResult IncrementIntValue(String token, LicenseKey license, String machineCode, String name, int intValue, boolean enableBound, int bound) {
        return IncrementIntValue(token, new IncrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, name, intValue, enableBound, bound));
    }

    public static BasicResult IncrementIntValue(String token, IncrementIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/incrementintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult IncrementIntValue(String token, IncrementIntValueToMachineCodeModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/incrementintvaluetomachinecode", model, extraParams, BasicResult.class);
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, long id, int intValue) {
        return DecrementIntValue(token, new DecrementIntValueToKeyModel(license.ProductId, license.Key, id, intValue, false, 0));
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, String name, int intValue) {
        return DecrementIntValue(token, new DecrementIntValueToKeyModel(license.ProductId, license.Key, name, intValue, false, 0));
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, String machineCode, long id, int intValue) {
        return DecrementIntValue(token, new DecrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, id, intValue, false, 0));
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, String machineCode, String name, int intValue) {
        return DecrementIntValue(token, new DecrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, name, intValue, false, 0));
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @param enableBound If set to true, it will be possible to specify a lower bound. For example, if you set the Bound
     *                    parameter (below) to 0, you will be able to decrement the int value until you reach zero (inclusive).
     *                    Once the lower bound is reached, an error will be thrown.
     * @param  bound This is the lower bound that will be enforced on the decrement operation. It will only be enforced if
     *               EnableBound is set to true. Please read the description above.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, long id, int intValue, boolean enableBound, int bound) {
        return DecrementIntValue(token, new DecrementIntValueToKeyModel(license.ProductId, license.Key, id, intValue, enableBound, bound));
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @param enableBound If set to true, it will be possible to specify a lower bound. For example, if you set the Bound
     *                    parameter (below) to 0, you will be able to decrement the int value until you reach zero (inclusive).
     *                    Once the lower bound is reached, an error will be thrown.
     * @param  bound This is the lower bound that will be enforced on the decrement operation. It will only be enforced if
     *               EnableBound is set to true. Please read the description above.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, String name, int intValue, boolean enableBound, int bound) {
        return DecrementIntValue(token, new DecrementIntValueToKeyModel(license.ProductId, license.Key, name, intValue, enableBound, bound));
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param id The unique object id for the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @param enableBound If set to true, it will be possible to specify a lower bound. For example, if you set the Bound
     *                    parameter (below) to 0, you will be able to decrement the int value until you reach zero (inclusive).
     *                    Once the lower bound is reached, an error will be thrown.
     * @param  bound This is the lower bound that will be enforced on the decrement operation. It will only be enforced if
     *               EnableBound is set to true. Please read the description above.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, String machineCode, long id, int intValue, boolean enableBound, int bound) {
        return DecrementIntValue(token, new DecrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, id, intValue, enableBound, bound));
    }

    /**
     * This method will decrement the integer value in a Data Object by a certain constant (non-negative).
     * You can always increment it. Note, this method does not allow integer overflows, i.e. if you decrement
     * by a constant that would result in an overflow, an error will be thrown. Note also that you can use the
     * Feature lock in the Access Token to specify the upper bound of the decrement constant. So, if you only
     * want to allow decrementing by 1, please set Feature lock field to 1 also. Please see Remarks for more
     * details (including access token setup).
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object.
     * @param intValue 	The constant int value that should be subtracted to the current IntValue of the data object.
     *                  For example, if this value is set to 5 and the old IntValue is 11, then the new IntValue
     *                  will be the old one minus 5, i.e. 6. Note, if you would set this value to -5 instead, the
     *                  same result would be achieved.
     * @param enableBound If set to true, it will be possible to specify a lower bound. For example, if you set the Bound
     *                    parameter (below) to 0, you will be able to decrement the int value until you reach zero (inclusive).
     *                    Once the lower bound is reached, an error will be thrown.
     * @param  bound This is the lower bound that will be enforced on the decrement operation. It will only be enforced if
     *               EnableBound is set to true. Please read the description above.
     * @return
     */
    public static BasicResult DecrementIntValue(String token, LicenseKey license, String machineCode, String name, int intValue, boolean enableBound, int bound) {
        return DecrementIntValue(token, new DecrementIntValueToMachineCodeModel(license.ProductId, license.Key, machineCode, name, intValue, enableBound, bound));
    }


    public static BasicResult DecrementIntValue(String token, DecrementIntValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/decrementintvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult DecrementIntValue(String token, DecrementIntValueToMachineCodeModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/decrementintvaluetomachinecode", model, extraParams, BasicResult.class);
    }

    /**
     * This method will assign a new string value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param id The unique object id for the data object.
     * @param stringValue A string value (text) to store. Max 10000 characters.
     * @return
     */
    public static BasicResult SetStringValue(String token, LicenseKey license, long id, String stringValue) {
        return SetStringValue(token, new SetStringValueToKeyModel(license.ProductId, license.Key, id, stringValue));
    }

    /**
     * This method will assign a new string value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object.
     * @param stringValue A string value (text) to store. Max 10000 characters.
     * @return
     */
    public static BasicResult SetStringValue(String token, LicenseKey license, String name, String stringValue) {
        return SetStringValue(token, new SetStringValueToKeyModel(license.ProductId, license.Key, name, stringValue));
    }

    /**
     * This method will assign a new string value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param id The unique object id for the data object.
     * @param stringValue A string value (text) to store. Max 10000 characters.
     * @return
     */
    public static BasicResult SetStringValue(String token, LicenseKey license, String machineCode, long id, String stringValue) {
        return SetStringValue(token, new SetStringValueToMachineCodeModel(license.ProductId, license.Key, machineCode, id, stringValue));
    }

    /**
     * This method will assign a new string value to a Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object.
     * @param stringValue A string value (text) to store. Max 10000 characters.
     * @return
     */
    public static BasicResult SetStringValue(String token, LicenseKey license, String machineCode, String name, String stringValue) {
        return SetStringValue(token, new SetStringValueToMachineCodeModel(license.ProductId, license.Key, machineCode, name, stringValue));
    }


    public static BasicResult SetStringValue(String token, SetStringValueToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setstringvaluetokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult SetStringValue(String token, SetStringValueToMachineCodeModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/setstringvaluetomachinecode", model, extraParams, BasicResult.class);
    }

    /**
     * This method will remove an existing Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param id The unique object id for the data object.
     * @return
     */
    public static BasicResult RemoveDataObject(String token, LicenseKey license, long id) {
        return RemoveDataObject(token, new RemoveDataObjectToKeyModel(license.ProductId, license.Key, id));
    }

    /**
     * This method will remove an existing Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param name The name of the data object.
     * @return
     */
    public static BasicResult RemoveDataObject(String token, LicenseKey license, String name) {
        return RemoveDataObject(token, new RemoveDataObjectToKeyModel(license.ProductId, license.Key, name));
    }

    /**
     * This method will remove an existing Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param id The unique object id for the data object.
     * @return
     */
    public static BasicResult RemoveDataObject(String token, LicenseKey license, String machineCode, long id) {
        return RemoveDataObject(token, new RemoveDataObjectToMachineCodeModel(license.ProductId, license.Key, machineCode, id));
    }

    /**
     * This method will remove an existing Data Object.
     * @param token The access token with 'SetIntValue' permission and KeyLock set to '-1'.
     * @param license The license key object (it's used to get the product id and key string).
     * @param machineCode The machine code.
     * @param name The name of the data object.
     * @return
     */
    public static BasicResult RemoveDataObject(String token, LicenseKey license, String machineCode, String name) {
        return RemoveDataObject(token, new RemoveDataObjectToMachineCodeModel(license.ProductId, license.Key, machineCode, name));
    }

    public static BasicResult RemoveDataObject(String token, RemoveDataObjectToKeyModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/removedataobjecttokey", model, extraParams, BasicResult.class);
    }

    public static BasicResult RemoveDataObject(String token, RemoveDataObjectToMachineCodeModel model) {

        Map<String,String> extraParams = new HashMap<>();

        extraParams.put("token", token);

        return HelperMethods.SendRequestToWebAPI("data/removedataobjecttomachinecode", model, extraParams, BasicResult.class);
    }
}
