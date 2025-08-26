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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
     * @apiNote If you do not want to depend on slf4j or if you use the cryptolens-android
     * binary, please call GetMachineCode with v=2.
     */
    public static String GetMachineCode() {

        return SHA256(getRawDeviceID());
    }

    /**
     * This method uses a special OS command to find the UUID of the device. In comparison
     * to the default method, GetMachineCode, this method does not depend on slf4j to compute
     * the device fingerprint, assuming that v=2. If v=2, the result of this method should be
     * the same as in the .NET SDK and Python on Windows.
     * <p>
     *     <strong>Note</strong>: If it is not possible to retrieve the UUID, this method will return null.
     *     Please keep this in mind and check for the null case.
     * </p>
     * @param v If set to 2, this method will use the UUID of the device instead of depending
     *          on slf4j. Note, it currently only supports Windows. You can read more
     *          here: https://help.cryptolens.io/faq/index#java.
     */
    public static String GetMachineCode(int v) {

        if (v == 1) {
            return GetMachineCode();
        }

        if(v== 3) {
            return getMachineCode_v3();
        }

        String operSys = System.getProperty("os.name").toLowerCase();

        if (operSys.contains("win")) {

            try {
                Process process = null;

                process = Runtime.getRuntime().exec("cmd /c powershell.exe -Command \"(Get-CimInstance -Class Win32_ComputerSystemProduct).UUID\"\n");

                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;

                StringBuilder sb = new StringBuilder();
                while (true) {
                    line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                }

                String res = sb.toString();
                String seed = res.toString().trim();

                if(seed == "") {
                    return null;
                }

                return SHA256(seed);

            } catch(Exception e){

                return null;
            }


        }else if (operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix")) {
            return "This OS is not supported yet.";
        } else if (operSys.contains("mac")) {
            return "This OS is not supported yet.";

        }
        else{
            return "This OS is not supported yet.";
        }

    }

    private static String getMachineCode_v3() {
        final String operSys = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

        try {
            String seed = null;

            if (operSys.contains("win")) {
                seed = getWindowsUUID();

            } else if (operSys.contains("mac")) {
                seed = getMacHardwareUUID();

            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix") || operSys.contains("linux")) {
                seed = getLinuxMachineSeed();

            } else {
                return "This OS is not supported yet.";
            }

            if (seed == null) {
                return null;
            }

            seed = seed.trim();
            if (seed.isEmpty()) {
                return null;
            }

            return sha256(seed);

        } catch (Exception e) {
            // Swallow to preserve current behavior; consider logging in real apps
            return null;
        }
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
     * @param license The license key object.
     * @param v If set to 2, this method will use the UUID of the device instead of depending
     *          on slf4j. Note, it currently only supports Windows. You can read more
     *          here: https://help.cryptolens.io/faq/index#java.
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, int v) {
        return IsOnRightMachine(license, v, false);
    }

    /**
     * Check if the device is registered with the license key.
     * @param license The license key object.
     * @param isFloatingLicense If this is a floating license, this parameter has to be set to true.
     *                          You can enable floating licenses by setting @see ActivateModel.FloatingTimeInterval.
     * @param v If set to 2, this method will use the UUID of the device instead of depending
     *          on slf4j. Note, it currently only supports Windows. You can read more
     *          here: https://help.cryptolens.io/faq/index#java.
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, int v, boolean isFloatingLicense) {
        return IsOnRightMachine(license, v, isFloatingLicense, false);
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
     * Check if the device is registered with the license key.
     * @param license The license key object.
     * @param v If set to 2, this method will use the UUID of the device instead of depending
     *          on slf4j. Note, it currently only supports Windows. You can read more
     *          here: https://help.cryptolens.io/faq/index#java.
     * @param isFloatingLicense If this is a floating license, this parameter has to be set to true.
     *                          You can enable floating licenses by setting @see ActivateModel.FloatingTimeInterval.
     * @param allowOverdraft If floating licensing is enabled with overdraft, this parameter should be set to true.
     *                       You can enable overdraft by setting ActivateModel.MaxOverdraft" to a value greater than 0.
     * @return True if the license is registered with this machine and False otherwise.
     */
    public static boolean IsOnRightMachine(LicenseKey license, int v, boolean isFloatingLicense, boolean allowOverdraft) {

        String current_mid =  Helpers.GetMachineCode(v);

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
            for (ActivatedMachine machine : license.ActivatedMachines) {
                if((machine.Mid.length() >= 9 && machine.Mid.substring(9).equals(current_mid) ||
                        allowOverdraft && machine.Mid.length() >= 19 && machine.Mid.substring(19).equals(current_mid))) {
                    return true;
                }
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

        if(features == null || features.trim() == "") {
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
    public static String SHA256(String rawData) {

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


    /* =========================
      Windows
      ========================= */
    private static String getWindowsUUID() throws IOException, InterruptedException {
        // Primary: PowerShell CIM
        String out = run(new ProcessBuilder(
                "powershell.exe", "-NoProfile", "-NonInteractive",
                "-Command", "(Get-CimInstance -Class Win32_ComputerSystemProduct).UUID"));
        out = firstNonEmptyLine(out);
        if (isLikelyUUID(out)) {
            return out;
        }

        // Fallback: WMIC (deprecated on newer Windows but still present on many)
        out = run(new ProcessBuilder("wmic", "csproduct", "get", "UUID"));
        // WMIC outputs a header line "UUID" and then the value—pick the first line that looks like a UUID
        for (String line : out.split("\\R")) {
            line = line.trim();
            if (isLikelyUUID(line)) return line;
        }
        return null;
    }

    /* =========================
       macOS
       ========================= */
    private static String getMacHardwareUUID() throws IOException, InterruptedException {
        // Matches: "Hardware UUID: XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX"
        String cmd = "system_profiler SPHardwareDataType | awk '/UUID/ { print $3; }'";
        String out = runShell(cmd);
        out = firstNonEmptyLine(out);
        if (isLikelyUUID(out)) {
            return out;
        }

        // Optional fallback using ioreg, if needed
        String alt = runShell("ioreg -rd1 -c IOPlatformExpertDevice | awk '/IOPlatformUUID/ {print $4}' | tr -d '\"'");
        alt = firstNonEmptyLine(alt);
        return isLikelyUUID(alt) ? alt : null;
    }

    /* =========================
       Linux
       ========================= */
    private static String getLinuxMachineSeed() throws IOException, InterruptedException {
        // 1) Raspberry Pi detection via /proc/device-tree/model (present on Pi; often missing in VMs/containers)
        String model = readFileIfExists("/proc/device-tree/model");
        if (model == null) {
            // Some environments need this alt path or may deny access to device-tree; not fatal.
            model = readFileIfExists("/sys/firmware/devicetree/base/model");
        }
        if (model != null && model.toLowerCase(Locale.ENGLISH).contains("raspberry")) {
            // Extract "Serial" from /proc/cpuinfo
            String cpuinfo = readFileIfExists("/proc/cpuinfo");
            if (cpuinfo != null) {
                for (String line : cpuinfo.split("\\R")) {
                    String t = line.trim();
                    if (t.toLowerCase(Locale.ENGLISH).startsWith("serial")) {
                        String[] parts = t.split(":", 2);
                        if (parts.length == 2) {
                            String serial = parts[1].trim();
                            if (!serial.isEmpty()) return serial;
                        }
                    }
                }
            }
            // If Pi but serial not found, continue to the generic path
        }

        // 2) Generic Linux: try DMI product UUID (no sudo required on many distros)
        String dmi = readFileIfExists("/sys/class/dmi/id/product_uuid");
        if (dmi != null) {
            dmi = firstNonEmptyLine(dmi);
            if (isLikelyUUID(dmi)) return dmi;
        }

        // 3) dmidecode (requires permissions; often needs sudo — will work in privileged envs)
        String dmiDecode = runMaybe(null, new ProcessBuilder("dmidecode", "-s", "system-uuid"));
        dmiDecode = firstNonEmptyLine(dmiDecode);
        if (isLikelyUUID(dmiDecode)) return dmiDecode;

        // 4) Your specified fallback when we cannot determine hardware:
        // Try UUID of /boot, then /boot/efi, then /
        String fsUuid = firstNonEmptyLine(runShell("findmnt --output=UUID --noheadings --target=/boot"));
        if (fsUuid == null || fsUuid.isEmpty()) {
            fsUuid = firstNonEmptyLine(runShell("findmnt --output=UUID --noheadings --target=/boot/efi"));
        }
        if (fsUuid == null || fsUuid.isEmpty()) {
            fsUuid = firstNonEmptyLine(runShell("findmnt --output=UUID --noheadings --target=/"));
        }
        if (fsUuid != null && !fsUuid.isEmpty()) {
            return fsUuid;
        }

        // 5) Last-resort for containerized / exotic hosts: OS installation id (not hardware!)
        String machineId = readFileIfExists("/etc/machine-id");
        if (machineId == null) {
            machineId = readFileIfExists("/var/lib/dbus/machine-id");
        }
        machineId = firstNonEmptyLine(machineId);
        if (machineId != null && !machineId.isEmpty()) {
            return machineId;
        }

        return null;
    }

    /* =========================
       Helpers
       ========================= */

    private static String runShell(String command) throws IOException, InterruptedException {
        // Use /bin/sh for portability across macOS and Linux
        return run(new ProcessBuilder("/bin/sh", "-c", command));
    }

    private static String run(ProcessBuilder pb) throws IOException, InterruptedException {
        pb.redirectErrorStream(true);
        Process p = pb.start();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (sb.length() > 0) sb.append('\n');
                sb.append(line);
            }
        }
        // Wait after draining streams to avoid deadlocks.
        p.waitFor();
        return sb.toString().trim();
    }

    private static String runMaybe(String defaultIfError, ProcessBuilder pb) {
        try {
            return run(pb);
        } catch (Exception e) {
            return defaultIfError;
        }
    }

    private static String readFileIfExists(String path) {
        try {
            Path p = Paths.get(path);
            if (!Files.exists(p)) return null;
            // Some files (like device-tree nodes) may contain NULs; read as bytes then clean
            byte[] bytes = Files.readAllBytes(p);
            String s = new String(bytes, StandardCharsets.UTF_8).replace("\u0000", "");
            return s.trim();
        } catch (Exception e) {
            return null;
        }
    }

    private static String firstNonEmptyLine(String text) {
        if (text == null) return null;
        for (String line : text.split("\\R")) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        return null;
    }

    private static boolean isLikelyUUID(String s) {
        if (s == null) return false;
        String t = s.trim();
        // Accept canonical UUIDs and common uppercase variants
        Pattern p = Pattern.compile("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$");
        Matcher m = p.matcher(t);
        if (m.matches()) return true;
        // Some firmware returns UUIDs without dashes; tolerate that too
        return t.matches("^[a-fA-F0-9]{32}$");
    }

    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                hex.append(Character.forDigit((b >>> 4) & 0xF, 16));
                hex.append(Character.forDigit(b & 0xF, 16));
            }
            return hex.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
