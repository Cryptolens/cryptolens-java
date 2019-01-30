package io.cryptolens.models;

import com.google.gson.Gson;
import io.cryptolens.JavaSecuritySignatureVerifier;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class LicenseKey {
    public int ProductId;
    public long Id;
    public String Key;
    public long Created;
    public long Expires;
    public int Period;
    public boolean F1;
    public boolean F2;
    public boolean F3;
    public boolean F4;
    public boolean F5;
    public boolean F6;
    public boolean F7;
    public boolean F8;
    public String Notes;
    public boolean Block;
    public long GlobalId;
    //  private GsonCustomer Customer;
    public List<ActivatedMachine> ActivatedMachines;
    public boolean TrialActivation;
    public int MaxNoOfMachines;
    //  private int AllowedMachines;
//  private List<DataObject> DataObjects;
    public long SignDate;
    public String RawResponse;

    /**
     * Save the current license object as a string. You can load it again using @see LoadFromString.
     * @return A string representing this license object object.
     */
    public String SaveAsString() {
        return RawResponse;
    }

    /**
     * Load a license string (created with @see SaveAsString) into a license object.
     * Note, signature verification will be performed under the hood.
     * @param RSAPubKey Your RSA Public Key, which can be found <a href="https://app.cryptolens.io/docs/api/v3/QuickStart">here</a>.
     * @param licenseString The license key object stored as a string.
     * @return A new license key object or null (if an error has occurred).
     */
    public static LicenseKey LoadFromString(String RSAPubKey, String licenseString) {

        ActivateResult result = new Gson().fromJson(licenseString, ActivateResult.class);

        Base64.Decoder decoder = Base64.getDecoder();

        byte[] licenseKey = decoder.decode(result.licenseKey);
        byte[] signature = decoder.decode(result.signature);

        try {

            JavaSecuritySignatureVerifier signatureVerifier = new JavaSecuritySignatureVerifier();
            int mstart = RSAPubKey.indexOf("<Modulus>");
            int mend = RSAPubKey.indexOf("</Modulus>");
            int estart = RSAPubKey.indexOf("<Exponent>");
            int eend = RSAPubKey.indexOf("</Exponent>");
            signatureVerifier.setModulusBase64(RSAPubKey.substring(mstart + 9, mend));
            signatureVerifier.setExponentBase64(RSAPubKey.substring(estart + 10, eend));

            if (!signatureVerifier.verify(licenseKey, signature)) {
                System.err.println("Signature check failed");
                return null;
            }
        } catch(Exception ex) {System.err.println(ex.getStackTrace());}

        String s = new String(licenseKey, StandardCharsets.UTF_8);
        LicenseKey license = new Gson().fromJson(s, LicenseKey.class);
        license.RawResponse = licenseString;

        return license;
    }
}
