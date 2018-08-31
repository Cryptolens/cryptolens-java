package io.cryptolens.tests.licensing

import com.google.gson.Gson
import io.cryptolens.licensing.methods.ActivateModel
import io.cryptolens.licensing.methods.internal.HelperMethods
import sun.misc.IOUtils

class HelperMethodsTest extends GroovyTestCase {

    void setUp() {
        super.setUp()
    }

    void tearDown() {

    }

    void testSendRequestToWebAPI3() {
        Gson gson = new Gson();

        ActivateModel params = new ActivateModel();
        params.ProductId = 3349;

        byte[] inputParams = gson.toJson(params).getBytes();

        InputStream res = HelperMethods.SendRequestToWebAPI3(inputParams, "Activate", "Activate");

        String jsonobj = IOUtils.toString(res);
    }
}

