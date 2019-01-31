package io.cryptolens.methods;

import io.cryptolens.models.ActivatedMachine;
import io.cryptolens.models.LicenseKey;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Helpers {

    /**
     * Returns a unique identifier of the device. Note, root access may be required.
     * Note, this method is not the same as the one used in our .NET client.
     */
    public static String GetMachineCode() {

        return SHA256(getRawDeviceID());
    }

    /**
     * Check if the device is registered with the license key.
     * @returns True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license) {
        return IsOnRightMachine(license, false);
    }

    /**
     * Check if the device is registered with the license key.
     * @param isFloatingLicense If this is a floating license, this parameter has to be set to true.
     *                          You can enable floating licenses by setting @see ActivateModel.FloatingTimeInterval.
     * @returns True if the license is registered with this machine and False otherwise.
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
     * @returns True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, boolean isFloatingLicense, boolean allowOverdraft) {

        String current_mid = Helpers.GetMachineCode();

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
        String processorIdentifier = centralProcessor.getIdentifier();
        int processors = centralProcessor.getLogicalProcessorCount();

        return vendor +
                processorSerialNumber +
                processorIdentifier +
                processors;
    }

}
