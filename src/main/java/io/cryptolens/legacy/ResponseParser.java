package io.cryptolens.legacy;
@Deprecated
public interface ResponseParser {
  public Base64Response parseBase64Response(String response);
  public LicenseKey parseLicenseKey(byte[] licenseKey);
}
