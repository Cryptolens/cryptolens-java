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
        license.Notes = "[\"test\", [\"module\",[\"A\"]]]";

        assertTrue(Helpers.HasFeature(license, "test"));
        assertTrue(Helpers.HasFeature(license, "module"));
        assertTrue(Helpers.HasFeature(license, "module.A"));
        assertFalse(Helpers.HasFeature(license, "module.B"));

        license.Notes = "[\"f1\", [\"f2\",[[\"voice\",[\"all\"]]]]]";

        assertTrue(Helpers.HasFeature(license, "f2.voice.all"));

        assertFalse(Helpers.HasFeature(license, "f2.voice.all.test"));
        assertFalse(Helpers.HasFeature(license, "f2.voice.all.test.a"));
        assertFalse(Helpers.HasFeature(license, "f2.A.all.test"));
        assertFalse(Helpers.HasFeature(license, "aa.voice.all.test"));

        String[] featurePath = "moduleA.video".split(".");

    }
}
