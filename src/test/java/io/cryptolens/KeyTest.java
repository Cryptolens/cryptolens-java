package io.cryptolens;

import io.cryptolens.methods.Key;
import io.cryptolens.models.ActivateModel;
import io.cryptolens.models.ActivateResult;
import io.cryptolens.models.BasicResult;
import io.cryptolens.models.LicenseKey;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class KeyTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public KeyTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( KeyTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception {

        String RSAPubKey = "<RSAKeyValue><Modulus>sGbvxwdlDbqFXOMlVUnAF5ew0t0WpPW7rFpI5jHQOFkht/326dvh7t74RYeMpjy357NljouhpTLA3a6idnn4j6c3jmPWBkjZndGsPL4Bqm+fwE48nKpGPjkj4q/yzT4tHXBTyvaBjA8bVoCTnu+LiC4XEaLZRThGzIn5KQXKCigg6tQRy0GXE13XYFVz/x1mjFbT9/7dS8p85n8BuwlY5JvuBIQkKhuCNFfrUxBWyu87CFnXWjIupCD2VO/GbxaCvzrRjLZjAngLCMtZbYBALksqGPgTUN7ZM24XbPWyLtKPaXF2i4XRR9u6eTj5BfnLbKAU5PIVfjIS+vNYYogteQ==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
        ActivateModel model = new ActivateModel();
        model.ProductId = 3349;
        model.Key = "ICVLD-VVSZR-ZTICT-YKGXL";

        LicenseKey aaa = Key.Activate("WyIyNjA5IiwiaWE5b0VFT3Q2eDlNR2FvbHBHK2VOYUZ4bzNjT3h5UkNrMCtiYnhPRSJd", RSAPubKey, model);

        System.out.println(aaa.Created);
        System.out.println(aaa.ActivatedMachines.get(0).Time);

        assertTrue( true );

    }
}
