package io.cryptolens.models;

import java.util.List;

public class LicenseKey {
    public int ProductId;
    public long Id;
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
    //  private GsonCustomer Customer;
    public List<ActivatedMachine> ActivatedMachines;
    public boolean TrialActivation;
    public int MaxNoOfMachines;
    //  private int AllowedMachines;
//  private List<DataObject> DataObjects;
    public long SignDate;
    public String RawResponse;
}
