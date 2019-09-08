package io.cryptolens;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cryptolens.internal.BasicResult;
import io.cryptolens.methods.Data;
import io.cryptolens.methods.Helpers;
import io.cryptolens.methods.ProductMethods;
import io.cryptolens.models.DataObject;
import io.cryptolens.models.GetProductsResult;
import io.cryptolens.models.LicenseKey;
import io.cryptolens.models.ListOfDataObjectsResult;
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
public class ProductTest
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
    public ProductTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ProductTest.class );
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

    public void testAdd() throws Exception{

        init();

        GetProductsResult getProducts = ProductMethods.GetProducts(APIKey.get("productmethods"));

        if(!Helpers.IsSuccessful(getProducts)) {
            fail("Could not obtain a list of data objects.");
        }

    }
}
