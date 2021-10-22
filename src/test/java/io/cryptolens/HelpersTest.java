package io.cryptolens;

import io.cryptolens.methods.Helpers;
import io.cryptolens.models.ActivatedMachine;
import io.cryptolens.models.DataObject;
import io.cryptolens.models.LicenseKey;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

        license.DataObjects = new ArrayList<>();

        DataObject dobj = new DataObject();

        dobj.Name = "cryptolens_features";
        dobj.StringValue = "[\"test\", [\"module\",[\"A\"]]]";
        license.DataObjects.add(dobj);

        assertTrue(Helpers.HasFeature(license, "test"));
        assertTrue(Helpers.HasFeature(license, "module"));
        assertTrue(Helpers.HasFeature(license, "module.A"));
        assertFalse(Helpers.HasFeature(license, "module.B"));

        license.DataObjects.get(0).StringValue = "[\"f1\", [\"f2\",[[\"voice\",[\"all\"]]]]]";

        assertTrue(Helpers.HasFeature(license, "f2.voice.all"));

        assertFalse(Helpers.HasFeature(license, "f2.voice.all.test"));
        assertFalse(Helpers.HasFeature(license, "f2.voice.all.test.a"));
        assertFalse(Helpers.HasFeature(license, "f2.A.all.test"));
        assertFalse(Helpers.HasFeature(license, "aa.voice.all.test"));

        String[] featurePath = "moduleA.video".split(".");

    }

    public void testMachineCode() throws IOException {

        Helpers.GetMachineCode();

        String mc = Helpers.GetMachineCode(2);

        LicenseKey license = new LicenseKey();
        license.ActivatedMachines = new ArrayList<>();
        ActivatedMachine machine = new ActivatedMachine();
        machine.Mid = mc;
        license.ActivatedMachines.add(machine);

        Helpers.IsOnRightMachine(license, 2);

        assertTrue(Helpers.IsOnRightMachine(license, 2));



    }
}
