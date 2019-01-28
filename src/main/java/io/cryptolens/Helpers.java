package io.cryptolens;

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
     * Furthermore, this method is not the same as the one used in our .NET client.
     */
    public static String GetMachineCode() {

        return SHA256(getRawDeviceID());
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
