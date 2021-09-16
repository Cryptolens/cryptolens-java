package io.cryptolens;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cryptolens.methods.Helpers;
import io.cryptolens.methods.Message;
import io.cryptolens.methods.ProductMethods;
import io.cryptolens.models.GetMessagesModel;
import io.cryptolens.models.GetMessagesResult;
import io.cryptolens.models.GetProductsResult;
import io.cryptolens.models.LicenseKey;
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
public class MessageTest
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
    public MessageTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MessageTest.class );
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

    public void testGetMessages() throws Exception{

        init();

        GetMessagesResult getMessages = Message.GetMessages(APIKey.get("getmessages"), new GetMessagesModel());

        if(!Helpers.IsSuccessful(getMessages)) {
            fail("Could not obtain a list of messages.");
        }

        for (int i = 0; i < getMessages.Messages.size(); i++) {
            System.out.println(getMessages.Messages.get(i).Content);

        }

    }
}
