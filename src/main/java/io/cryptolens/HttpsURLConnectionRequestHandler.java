package io.cryptolens;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;

import javax.net.ssl.*;

import com.google.gson.*;

import java.math.BigInteger;
import java.security.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.*;

public class HttpsURLConnectionRequestHandler implements RequestHandler {
  public String makePostRequest(String url, Map<String,String> params) throws Exception {
    URL url_ = new URL(url);
    HttpsURLConnection connection = (HttpsURLConnection)url_.openConnection();
    connection.setRequestMethod("POST");

    connection.setDoOutput(true);
    BufferedOutputStream request = new BufferedOutputStream(connection.getOutputStream());
    byte[] body = paramString(params).getBytes();
    request.write(body);
    request.close();

    int status = connection.getResponseCode();
    System.out.println(status);

    System.out.println(connection.getResponseMessage());

    char[] buffer = new char[2048];
    Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder sb = new StringBuilder();
    while (true) {
      int n = in.read(buffer);
      if (n == -1) { break; }
      sb.append(buffer, 0, n);
    }
    in.close();

    return sb.toString();
  }

  private static String paramString(Map<String,String> params) throws Exception {
    StringBuilder sb = new StringBuilder();
 
    boolean first = true;
    for (Map.Entry<String,String> entry : params.entrySet()) {
      if (first) { first = false; }
      else       { sb.append("&"); }

      sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
        .append("=")
        .append(URLEncoder.encode(entry.getValue(), "UTF-8"));
    }
 
    return sb.toString();
  }
}
