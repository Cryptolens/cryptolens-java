package io.cryptolens.legacy;
@Deprecated
public class LicenseKey {
  public static class Builder {
    private final LicenseKey licenseKey;

    public Builder() {
      licenseKey = new LicenseKey();
    }

    public LicenseKey build() {
      return licenseKey;
    }

    public void setProductId(int productId) {
      licenseKey.productId = productId;
    }

    public void setId(long id) {
      licenseKey.id = id;
    }

    public void setKey(String key) {
      licenseKey.key = key;
    }

    public void setCreated(long created) {
      licenseKey.created = created;
    }

    public void setExpires(long expires) {
      licenseKey.expires = expires;
    }

    public void setPeriod(int period) {
      licenseKey.period = period;
    }

    public void setF1(boolean f1) {
      licenseKey.f1 = f1;
    }

    public void setF2(boolean f2) {
      licenseKey.f2 = f2;
    }

    public void setF3(boolean f3) {
      licenseKey.f3 = f3;
    }

    public void setF4(boolean f4) {
      licenseKey.f4 = f4;
    }

    public void setF5(boolean f5) {
      licenseKey.f5 = f5;
    }

    public void setF6(boolean f6) {
      licenseKey.f6 = f6;
    }

    public void setF7(boolean f7) {
      licenseKey.f7 = f7;
    }

    public void setF8(boolean f8) {
      licenseKey.f8 = f8;
    }

    public void setNotes(String notes) {
      licenseKey.notes = notes;
    }

    public void setBlock(boolean block) {
      licenseKey.block = block;
    }

    public void setGlobalId(long globalId) {
      licenseKey.globalId = globalId;
    }

/*
    public void setCustomer {
      licenseKey.Customer = Customer;
    }

    public void setActivatedMachines {
      licenseKey.ActivatedMachines = ActivatedMachines;
    }
*/

    public void setTrialActivation(boolean trialActivation) {
      licenseKey.trialActivation = trialActivation;
    }

    public void setMaxNoOfMachines(int maxNoOfMachines) {
      licenseKey.maxNoOfMachines = maxNoOfMachines;
    }

/*
    public void setAllowedMachines {
      licenseKey.AllowedMachines = AllowedMachines;
    }
*/

/*
    public void setDataObjects {
      licenseKey.DataObjects = DataObjects;
    }
*/

    public void setSignDate(long signDate) {
      licenseKey.signDate = signDate;
    }
  }

  private int productId;
  private long id;
  private String key;
  private long created;
  private long expires;
  private int period;
  private boolean f1;
  private boolean f2;
  private boolean f3;
  private boolean f4;
  private boolean f5;
  private boolean f6;
  private boolean f7;
  private boolean f8;
  private String notes;
  private boolean block;
  private long globalId;
//  private GsonCustomer Customer;
//  private List<ActivatedMachine> ActivatedMachines;
  private boolean trialActivation;
  private int maxNoOfMachines;
//  private int AllowedMachines;
//  private List<DataObject> DataObjects;
  private long signDate;

  public int getProductId() {
    return productId;
  }

  public long getId() {
    return id;
  }

  public String getKey() {
    return key;
  }

  public long getCreated() {
    return created;
  }

  public long getExpires() {
    return expires;
  }

  public int getPeriod() {
    return period;
  }

  public boolean getF1() {
    return f1;
  }

  public boolean getF2() {
    return f2;
  }

  public boolean getF3() {
    return f3;
  }

  public boolean getF4() {
    return f4;
  }

  public boolean getF5() {
    return f5;
  }

  public boolean getF6() {
    return f6;
  }

  public boolean getF7() {
    return f7;
  }

  public boolean getF8() {
    return f8;
  }

  public String getNotes() {
    return notes;
  }

  public boolean getBlock() {
    return block;
  }

  public long getGlobalId() {
    return globalId;
  }

/*
  public GsonCustomer getCustomer() {
    return Customer;
  }

  public List<ActivatedMachine> getActivatedMachines() {
    return ActivatedMachines;
  }
*/

  public boolean getTrialActivation() {
    return trialActivation;
  }

  public int getMaxNoOfMachines() {
    return maxNoOfMachines;
  }

/*
  public int getAllowedMachines() {
    return AllowedMachines;
  }
*/

/*
  public List<DataObject> getDataObjects() {
    return DataObjects;
  }
*/

  public long getSignDate() {
    return signDate;
  }
}
