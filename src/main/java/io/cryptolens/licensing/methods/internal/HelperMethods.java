package io.cryptolens.licensing.methods.internal;

import java.io.InputStream;
import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;


public class HelperMethods {

    private static String SERVER = "https://app.cryptolens.io/api/";

    /**
     * Calls the Web API 3 and returns the raw response.
     */
    public static InputStream SendRequestToWebAPI3(byte[] inputParams, String typeOfAction, String token) {

        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(SERVER + typeOfAction).openConnection();

            // Compress the data to save bandwidth
            //byte[] compressedData = compress(data.toString());
            // Add headers
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Accept", "application/json");
            connection.addRequestProperty("Connection", "close");
            //connection.addRequestProperty("Content-Encoding", "gzip"); // We gzip our request
            connection.addRequestProperty("Content-Length", Integer.toString(inputParams.length));
            connection.setRequestProperty("Content-Type", "application/json"); // We send our data in JSON format
            connection.setRequestProperty("User-Agent", "cryptolens-java-v10");
            connection.setRequestProperty("token",token);

            // Send data
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(inputParams);
            outputStream.flush();
            outputStream.close();

            return connection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
