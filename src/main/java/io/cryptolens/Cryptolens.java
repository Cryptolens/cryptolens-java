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

public class Cryptolens {
  private JavaSecuritySignatureVerifier signatureVerifier;
  private RequestHandler requestHandler;
  private GsonResponseParser responseParser;

  private Cryptolens() {}

  public static Cryptolens getDefault() {
    Cryptolens cryptolens = new Cryptolens();

    cryptolens.signatureVerifier = new JavaSecuritySignatureVerifier();
    cryptolens.requestHandler = new HttpsURLConnectionRequestHandler();
    cryptolens.responseParser = new GsonResponseParser();

    return cryptolens;
  }

  public void setExponentBase64(String exponent) {
    signatureVerifier.setExponentBase64(exponent);
  }

  public void setModulusBase64(String modulus) {
    signatureVerifier.setModulusBase64(modulus);
  }

  public ActivateResponse activate(String token, int productId, String licenseKey, String machineCode) {
    try {

    Map<String,String> params = new HashMap<>();
    params.put("token", token);
    params.put("ProductId", Integer.toString(productId));
    params.put("Key", licenseKey);
    params.put("MachineCode", machineCode);
    params.put("Sign", "true");
    params.put("SignMethod", "1");
//    params.put(

    String response = requestHandler.makePostRequest("https://app.cryptolens.io/api/key/Activate", params);
    Base64Response base64response = responseParser.parseBase64Response(response);

    if (base64response.message != null) { return new ActivateResponse(parseServerMessage(base64response.message)); }

    if (!signatureVerifier.verify(base64response.licenseKey, base64response.signature)) {
      System.err.println("Signature check failed");
      return new ActivateResponse(new RuntimeException("Invalid signature"));
    }
    return new ActivateResponse(responseParser.parseLicenseKey(base64response.licenseKey));

    } catch (Exception e) {
      return new ActivateResponse(e);
    }
  }

  public static class ActivateResponse {
    private ActivateServerError serverError;
    private Exception exception;
    private LicenseKey licenseKey;

    public ActivateResponse(ActivateServerError serverErrror) {
      this.serverError = serverError;
      this.exception = null;
      this.licenseKey = null;
    }

    public ActivateResponse(Exception exception) {
      this.serverError = null;
      this.exception = exception;
      this.licenseKey = null;
    }

    public ActivateResponse(LicenseKey licenseKey) {
      this.serverError = null;
      this.exception = null;
      this.licenseKey = licenseKey;
    }

    public LicenseKey getLicenseKey() {
      return licenseKey;
    }

    public boolean successful() {
      return licenseKey != null;
    }

    public Exception getException() {
      return exception;
    }

    public ActivateServerError getServerError() {
      return serverError;
    }
  }

  public static enum ActivateServerError {
    INVALID_ACCESS_TOKEN, ACCESS_DENIED, INCORRECT_INPUT_PARAMETER, PRODUCT_NOT_FOUND,
    KEY_NOT_FOUND, KEY_BLOCKED, DEVICE_LIMIT_REACHED, UNKNOWN_SERVER_REPLY
  }

  private static ActivateServerError parseServerMessage(String message) {
    if ("Unable to authenticate.".equals(message)) {
      return ActivateServerError.INVALID_ACCESS_TOKEN;
    }

    if ("Access denied.".equals(message)) {
      return ActivateServerError.ACCESS_DENIED;
    }

    if ("The input parameters were incorrect.".equals(message)) {
      return ActivateServerError.INCORRECT_INPUT_PARAMETER;
    }

    if ("Could not find the product.".equals(message)) {
      return ActivateServerError.PRODUCT_NOT_FOUND;
    }

    if ("Could not find the key.".equals(message)) {
      return ActivateServerError.KEY_NOT_FOUND;
    }

    if ("The key is blocked and cannot be accessed.".equals(message)) {
      return ActivateServerError.KEY_BLOCKED;
    }

    if ("Cannot activate the new device as the limit has been reached.".equals(message)) {
      return ActivateServerError.DEVICE_LIMIT_REACHED;
    }

    return ActivateServerError.UNKNOWN_SERVER_REPLY;
  }
}
