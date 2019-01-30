package io.cryptolens;

import io.cryptolens.methods.Key;
import io.cryptolens.models.ActivateModel;
import io.cryptolens.models.BasicResult;
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

        ActivateModel model = new ActivateModel();
        model.ProductId = 3349;

        BasicResult aaa = (BasicResult) Key.Activate("WyIyNjA5IiwiaWE5b0VFT3Q2eDlNR2FvbHBHK2VOYUZ4bzNjT3h5UkNrMCtiYnhPRSJd", model);

        System.out.println(aaa.Message);
        System.out.println(aaa.Result);

        assertTrue( true );

    }
}
