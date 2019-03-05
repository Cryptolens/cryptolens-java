package io.cryptolens;

import io.cryptolens.methods.Helpers;
import io.cryptolens.models.LicenseKey;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class HelpersTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public HelpersTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( HelpersTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        LicenseKey license = new LicenseKey();
        license.Notes = "[\"test\"]";

        assertTrue(Helpers.HasFeature(license, "test"));
    }
}
