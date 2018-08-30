package io.cryptolens.licensing.methods.internal;

import java.io.InputStream;

import org.glassfish.jersey.core;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelperMethods {

    private static String SERVER = "https://app.cryptolens.io/api/";

    /**
     * Calls the Web API 3 and returns the raw response.
     */
    public static InputStream SendRequestToWebAPI3(Object inputParams, String typeOfAction, String token) {

        Client client = ClientBuilder.newClient();

        WebTarget resource = client.target("http://localhost:8080/someresource");

        Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);

        Response response = request.get();

        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity());
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity());
        }
        return null;
    }

}
