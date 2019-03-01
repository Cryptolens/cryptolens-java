package io.cryptolens;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cryptolens.methods.Helpers;
import io.cryptolens.methods.Key;
import io.cryptolens.models.ActivateModel;
import io.cryptolens.models.LicenseKey;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


/**
 * Unit test for simple App.
 */
public class DataTest
    extends TestCase
{

    HashMap<String, String> APIKey = null;


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception {

        String api_content = new String(Files.readAllBytes(Paths.get("apikeys.json")), "UTF-8");
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        APIKey = new Gson().fromJson(api_content, type);

        assertTrue( true );

    }

    public void testIncrement() {

        //assertTrue(false);
    }
}
