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

public interface ResponseParser {
  public Base64Response parseBase64Response(String response);
  public LicenseKey parseLicenseKey(byte[] licenseKey);
}
