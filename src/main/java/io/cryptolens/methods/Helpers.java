package io.cryptolens.methods;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import io.cryptolens.internal.BasicResult;
import io.cryptolens.models.ActivatedMachine;
import io.cryptolens.models.LicenseKey;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


/**
 * A collection of helper methods that operate on a license key.
 *
 * If you are using <b>cryptolens-android.jar</b>, please avoid using
 * <b>GetMachineCode</b> and all versions of <b>IsOnRightMachine</b> that do not
 * take in a custom machine code string.
 */
public class Helpers {

    /**
     * Returns a unique identifier of the device. Note, root access may be required.
     * Note, this method is not the same as the one used in our .NET client.
     * Also, this method only works on desktop computers.
     */
    public static String GetMachineCode() {

        return SHA256(getRawDeviceID());
    }

    /**
     * Check if the device is registered with the license key.
     * @param license The license key object.
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license) {
        return IsOnRightMachine(license, false);
    }

    /**
     * Check if the device is registered with the license key.
     * @param license The license key object.
     * @param isFloatingLicense If this is a floating license, this parameter has to be set to true.
     *                          You can enable floating licenses by setting @see ActivateModel.FloatingTimeInterval.
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, boolean isFloatingLicense) {
        return IsOnRightMachine(license, isFloatingLicense, false);
    }

    /**
     * Check if the device is registered with the license key.
     * @param license The license key object
     * @param isFloatingLicense If this is a floating license, this parameter has to be set to true.
     *                          You can enable floating licenses by setting @see ActivateModel.FloatingTimeInterval.
     * @param allowOverdraft If floating licensing is enabled with overdraft, this parameter should be set to true.
     *                       You can enable overdraft by setting ActivateModel.MaxOverdraft" to a value greater than 0.
     *
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, boolean isFloatingLicense, boolean allowOverdraft) {

        String current_mid = Helpers.GetMachineCode();
        return IsOnRightMachine(license, current_mid, isFloatingLicense, allowOverdraft);
    }


    /**
     * Check if the device is registered with the license key. This method is useful for platforms where the
     * GetMachineCode() is not supported, eg. on Android.
     * @param license The license key object.
     * @param machineCode The machine code of the current device.
     *
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, String machineCode) {
        return IsOnRightMachine(license, machineCode, false, false);
    }

    /**
     * Check if the device is registered with the license key. This method is useful for platforms where the
     * GetMachineCode() is not supported, eg. on Android.
     * @param license The license key object.
     * @param machineCode The machine code of the current device.
     * @param isFloatingLicense If this is a floating license, this parameter has to be set to true.
     *                          You can enable floating licenses by setting @see ActivateModel.FloatingTimeInterval.
     *
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, String machineCode, boolean isFloatingLicense) {
        return IsOnRightMachine(license, machineCode, isFloatingLicense, false);
    }

    /**
     * Check if the device is registered with the license key. This method is useful for platforms where the
     * GetMachineCode() is not supported, eg. on Android.
     * @param license The license key object.
     * @param machineCode The machine code of the current device.
     * @param isFloatingLicense If this is a floating license, this parameter has to be set to true.
     *                          You can enable floating licenses by setting @see ActivateModel.FloatingTimeInterval.
     * @param allowOverdraft If floating licensing is enabled with overdraft, this parameter should be set to true.
     *                       You can enable overdraft by setting ActivateModel.MaxOverdraft" to a value greater than 0.
     *
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, String machineCode, boolean isFloatingLicense, boolean allowOverdraft) {

        String current_mid = machineCode;

        if (license == null || license.ActivatedMachines == null){
            return false;
        }

        if(isFloatingLicense) {
            if(license.ActivatedMachines.size() == 1 &&
                    (license.ActivatedMachines.get(0).Mid.substring(9).equals(current_mid) ||
                            allowOverdraft && license.ActivatedMachines.get(0).Mid.substring(19).equals(current_mid))) {
                return true;
            }
        } else {

            for (ActivatedMachine machine : license.ActivatedMachines) {
                if(machine.Mid.equals(current_mid))
                    return true;
            }
        }

        return false;
    }


    /**
     * Check if the current license has expired.
     * @param licenseKey a license key object.
     * @return True if it has expired and false otherwise.
     */
    public static boolean HasExpired(LicenseKey licenseKey) {

        if(licenseKey == null) {
            return false;
        }

        long unixTime = System.currentTimeMillis() / 1000L;

        if (licenseKey.Expires < unixTime) {
            return true;
        }

        return false;
    }

    /**
     * Check if the current license has not expired.
     * @param licenseKey a license key object.
     * @return True if it has not expired and false otherwise.
     */
    public static boolean HasNotExpired(LicenseKey licenseKey) {

        return !Helpers.HasExpired(licenseKey);
    }

    /**
     * Check if the license has a certain feature enabled (i.e. set to true).
     * @param licenseKey a license key object.
     * @param feature The feature, eg 1 to 8.
     * @return If the feature is set to true, true is returned and false otherwise.
     */
    public static boolean HasFeature(LicenseKey licenseKey, int feature) {

        if(licenseKey == null){
            return false;
        }

        if (feature == 1 && licenseKey.F1)
            return true;
        if (feature == 2 && licenseKey.F2)
            return true;
        if (feature == 3 && licenseKey.F3)
            return true;
        if (feature == 4 && licenseKey.F4)
            return true;
        if (feature == 5 && licenseKey.F5)
            return true;
        if (feature == 6 && licenseKey.F6)
            return true;
        if (feature == 7 && licenseKey.F7)
            return true;
        if (feature == 8 && licenseKey.F8)
            return true;

        return false;
    }

    public class MyClass<T>
    {
        //public String par1;
        public T par2;
    }

    /**
     * <p>Uses a special data object associated with the license key to determine if a certain feature exists (instead of the 8 feature flags).</p>
     * <p><strong>Formatting: </strong> The name of the data object should be 'cryptolens_features' and it should be structured as a JSON array.</p>
     * <p>For example,</p> <pre>["f1", "f2"]</pre><p>means f1 and f2 are true. You can also have feature bundling, eg. </p> <pre>["f1", ["f2",["voice","image"]]]</pre>
     * <p>which means that f1 and f2 are true, as well as f2.limited and f2.image. You can set any depth, eg. you can have</p>
     * <pre>["f1", ["f2",[["voice",["all"]], "image"]]]</pre> <p>means f2.voice.all is true as well as f2.voice and f2.
     * The dots symbol is used to specify the "sub-features". </p>
     * @param licenseKey a license key object.
     * @param featureName the name of the feature (case-sensitive).
     * @return True if the feature exists and false otherwise.
     */
    public static boolean HasFeature(LicenseKey licenseKey, String featureName) {

        if(licenseKey.DataObjects == null) {
            return false;
        }

        String features = null;
        for(int i = 0; i < licenseKey.DataObjects.size(); i++) {
            if(licenseKey.DataObjects.get(i).Name.equals("cryptolens_features")) {
                features = licenseKey.DataObjects.get(i).StringValue;
            }
        }

        if(features == null) {
            // data object not found.
            return false;
        }

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(features).getAsJsonArray();
        String[] featurePath = featureName.split("\\.");

        boolean found = false;
        for(int i = 0; i < featurePath.length; i++) {
            found = false;
            int index = -1;
            for(int j = 0; j < array.size(); j++) {

                if(!array.get(j).isJsonArray() && array.get(j).getAsString().equals(featurePath[i])) {
                    found = true;
                    break;
                } else if (array.get(j).isJsonArray() && array.get(j).getAsJsonArray().get(0).getAsString().equals(featurePath[i])){
                    found = true;
                    index = j;
                    break;
                }
            }
            if(!found){
                return false;
            }
            if(i + 1 < featurePath.length && index != -1) {
                // still have some sub features to go through.
                // TODO: need to check if it's actually a json array or null?
                // TODO: try catch
                array = array.get(index).getAsJsonArray().get(1).getAsJsonArray();
            }
        }

        if(!found){
            return false;
        }
        return true;
    }

    /**
     * Return the sha256 checksum of a string.
     */
    private static String SHA256(String rawData) {

        StringBuffer hexString = new StringBuffer();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawData.getBytes(StandardCharsets.UTF_8));

            //thanks to https://stackoverflow.com/a/5470268.

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }

            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks if a response from Cryptolens is successful.
     * @param result The response from an API call. All responses inherit from BasicResult.
     * @return True if the response is successful and false otherwise.
     */
    public static boolean IsSuccessful(BasicResult result) {
        if(result == null || result.result == 1)
            return false;
        return true;
    }

    private static String getRawDeviceID()
    {
        //thanks to https://stackoverflow.com/a/37705082. may require root.
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
        ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();

        String vendor = operatingSystem.getManufacturer();
        String processorSerialNumber = computerSystem.getSerialNumber();
        String processorIdentifier = centralProcessor.getProcessorIdentifier().getIdentifier();
        int processors = centralProcessor.getLogicalProcessorCount();

        return vendor +
                processorSerialNumber +
                processorIdentifier +
                processors;
    }

}
