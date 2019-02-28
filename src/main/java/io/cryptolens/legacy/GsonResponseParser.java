package io.cryptolens.legacy;

import java.nio.charset.*;

import com.google.gson.*;

import java.util.Base64;
@Deprecated
public class GsonResponseParser implements ResponseParser {
  private static class GsonBase64Response {
    public String licenseKey;
    public String signature;
    public int result;
    public String message;
  }

// {"ProductId":3646,"ID":4,"Key":"MPDWY-PQAOW-FKSCH-SGAAU","Created":1490313600,"Expires":1492905600,"Period":30,"F1":false,"F2":false,"F3":false,"F4":false,"F5":false,"F6":false,"F7":false,"F8":false,"Notes":null,"Block":false,"GlobalId":31876,"Customer":null,"ActivatedMachines":[{"Mid":"","IP":"155.4.134.27","Time":1491898918},{"Mid":"289jf2afs3","IP":"155.4.134.27","Time":1491900636},{"Mid":"289jf2afsf","IP":"155.4.134.27","Time":1491900546},{"Mid":"lol","IP":"155.4.134.27","Time":1491898995}],"TrialActivation":false,"MaxNoOfMachines":1,"AllowedMachines":"","DataObjects":[],"SignDate":1535735713}

  private static class GsonLicenseKey {
    public int ProductId;
    public long ID;
    public String Key;
    public long Created;
    public long Expires;
    public int Period;
    public boolean F1;
    public boolean F2;
    public boolean F3;
    public boolean F4;
    public boolean F5;
    public boolean F6;
    public boolean F7;
    public boolean F8;
    public String Notes;
    public boolean Block;
    public long GlobalId;
//    public GsonCustomer Customer;
//    public List<ActivatedMachine> ActivatedMachines;
    public boolean TrialActivation;
    public int MaxNoOfMachines;
//    public int AllowedMachines;
//    public List<DataObject> DataObjects;
    public long SignDate;

    public LicenseKey toLicenseKey() {
      LicenseKey.Builder builder = new LicenseKey.Builder();

      builder.setProductId(ProductId);
      builder.setId(ID);
      builder.setKey(Key);
      builder.setCreated(Created);
      builder.setExpires(Expires);
      builder.setPeriod(Period);
      builder.setF1(F1);
      builder.setF2(F2);
      builder.setF3(F3);
      builder.setF4(F4);
      builder.setF5(F5);
      builder.setF6(F6);
      builder.setF7(F7);
      builder.setF8(F8);
      builder.setNotes(Notes);
      builder.setBlock(Block);
      builder.setGlobalId(GlobalId);
//      builder.setCustomer(Customer);
//      builder.setActivatedMachines(ActivatedMachines);
      builder.setTrialActivation(TrialActivation);
      builder.setMaxNoOfMachines(MaxNoOfMachines);
//      builder.setAllowedMachines(AllowedMachines);
//      builder.setDataObjects(DataObjects);
      builder.setSignDate(SignDate);

      return builder.build();
    }
  }

  private static final Gson gson = new Gson();

  public Base64Response parseBase64Response(String response) {
    GsonBase64Response resp = gson.fromJson(response, GsonBase64Response.class);

    if (resp.result != 0) { return new Base64Response(resp.message); }


    byte[] licenseKey = Shared.defaultBase64Decoder(resp.licenseKey);
    byte[] signature  = Shared.defaultBase64Decoder(resp.signature);

    return new Base64Response(licenseKey, signature);
  }

  public LicenseKey parseLicenseKey(byte[] licenseKey) {
    String s = new String(licenseKey, StandardCharsets.UTF_8);
    GsonLicenseKey g = gson.fromJson(s, GsonLicenseKey.class);
    return g.toLicenseKey();
  }
}
