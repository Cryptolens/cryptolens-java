package io.cryptolens;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cryptolens.methods.Helpers;
import io.cryptolens.methods.Key;
import io.cryptolens.models.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class KeyTest
    extends TestCase {

    HashMap<String, String> APIKey = null;


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public KeyTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(KeyTest.class);
    }

    public void init() throws Exception{
        String api_content = new String(Files.readAllBytes(Paths.get("apikeys.json")), "UTF-8");
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        APIKey = new Gson().fromJson(api_content, type);

        assertTrue( true );

    }


    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception {

        init();

        String RSAPubKey = "<RSAKeyValue><Modulus>sGbvxwdlDbqFXOMlVUnAF5ew0t0WpPW7rFpI5jHQOFkht/326dvh7t74RYeMpjy357NljouhpTLA3a6idnn4j6c3jmPWBkjZndGsPL4Bqm+fwE48nKpGPjkj4q/yzT4tHXBTyvaBjA8bVoCTnu+LiC4XEaLZRThGzIn5KQXKCigg6tQRy0GXE13XYFVz/x1mjFbT9/7dS8p85n8BuwlY5JvuBIQkKhuCNFfrUxBWyu87CFnXWjIupCD2VO/GbxaCvzrRjLZjAngLCMtZbYBALksqGPgTUN7ZM24XbPWyLtKPaXF2i4XRR9u6eTj5BfnLbKAU5PIVfjIS+vNYYogteQ==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
        String auth = APIKey.get("getkeyactivate");

        APIError error = new APIError();

        LicenseKey license = Key.Activate(auth, RSAPubKey, new ActivateModel(3349, "ICVLD-VVSZR-ZTICT-YKGXL", Helpers.GetMachineCode()), error);

        if (license == null || !Helpers.IsOnRightMachine(license)) {
            System.out.println("The license does not work.");
            System.out.println("Error: " + error.message);
        } else {
            System.out.println("The license is valid!");
            System.out.println("It will expire: " + license.Expires);

            System.out.println(license.Customer.Created);

            String licenseString = license.SaveAsString();

            LicenseKey newLicense = LicenseKey.LoadFromString(RSAPubKey, licenseString);

            System.out.println(newLicense.Expires);
        }

        //Key.Deactivate("", new DeactivateModel(3349, "ICVLD-VVSZR-ZTICT-YKGXL", Helpers.GetMachineCode()));
        System.out.println(Helpers.GetMachineCode());

        assertTrue(true);

    }


    public void testGetKey() throws Exception {

        init();

        String RSAPubKey = "<RSAKeyValue><Modulus>sGbvxwdlDbqFXOMlVUnAF5ew0t0WpPW7rFpI5jHQOFkht/326dvh7t74RYeMpjy357NljouhpTLA3a6idnn4j6c3jmPWBkjZndGsPL4Bqm+fwE48nKpGPjkj4q/yzT4tHXBTyvaBjA8bVoCTnu+LiC4XEaLZRThGzIn5KQXKCigg6tQRy0GXE13XYFVz/x1mjFbT9/7dS8p85n8BuwlY5JvuBIQkKhuCNFfrUxBWyu87CFnXWjIupCD2VO/GbxaCvzrRjLZjAngLCMtZbYBALksqGPgTUN7ZM24XbPWyLtKPaXF2i4XRR9u6eTj5BfnLbKAU5PIVfjIS+vNYYogteQ==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
        String auth = APIKey.get("getkeyactivate");

        APIError error = new APIError();

        LicenseKey license = Key.GetKey(auth, RSAPubKey, new GetKeyModel(3349, "ICVLD-VVSZR-ZTICT-YKGXL"), error);

        if (license == null) {
            System.out.println("The license does not work.");
            System.out.println("Error: " + error.message);
        } else {
            System.out.println("The license is valid!");
            System.out.println("It will expire: " + license.Expires);
        }

        assertTrue(true);

    }

}
