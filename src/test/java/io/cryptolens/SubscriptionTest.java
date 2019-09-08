package io.cryptolens;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cryptolens.internal.BasicResult;
import io.cryptolens.methods.Helpers;
import io.cryptolens.methods.ProductMethods;
import io.cryptolens.methods.Subscription;
import io.cryptolens.models.GetProductsResult;
import io.cryptolens.models.LicenseKey;
import io.cryptolens.models.RecordUsageModel;
import io.cryptolens.models.RegisterEventModel;
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
public class SubscriptionTest
    extends TestCase
{

    HashMap<String, String> APIKey = null;
    LicenseKey license = null;
    String machineCode = "2FE620C9C62F6A8BBD17F2AF49E12434B7C2CFC67FD2F48C2CB090893C4B4694";

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SubscriptionTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SubscriptionTest.class );
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


    }

    public void testRecordUsage() throws Exception{

        init();

        BasicResult res = Subscription.RecordUsage(APIKey.get("subscriptionmethods"),
                new RecordUsageModel(3349, "CMXKC-GUQRW-EJUGS-RRPUR", 1));

        if(!Helpers.IsSuccessful(res)) {
            fail("Could not register usage");
        }

    }
}
