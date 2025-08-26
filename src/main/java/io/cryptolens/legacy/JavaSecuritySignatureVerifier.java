package io.cryptolens.legacy;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.net.*;


import com.google.gson.*;

import java.math.BigInteger;
import java.security.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.RSAPublicKeySpec;


@Deprecated
public class JavaSecuritySignatureVerifier {
  private BigInteger mod;
  private BigInteger exp;

  public boolean verify(byte[] message, byte[] signature) throws Exception {

    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(mod, exp);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey key = keyFactory.generatePublic(keySpec);
    Signature sig = Signature.getInstance("SHA256withRSA");

    sig.initVerify(key);
    sig.update(message);
    return sig.verify(signature);
  }

  public void setExponentBase64(String exponent) {

    exp = new BigInteger(1, Shared.defaultBase64Decoder(exponent));
  }

  public void setModulusBase64(String modulus) {
    mod = new BigInteger(1, Shared.defaultBase64Decoder(modulus));
  }
}
