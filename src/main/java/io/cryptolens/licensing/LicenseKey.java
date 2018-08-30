package io.cryptolens.licensing;

import java.time.LocalDateTime;
import java.util.Set;

public class LicenseKey {

    public int ProductId;
    public int Id;
    public String Key;
    public LocalDateTime Created;
    public LocalDateTime Expires;
    public int Period;

    public Boolean F1;
    public Boolean F2;
    public Boolean F3;
    public Boolean F4;
    public Boolean F5;
    public Boolean F6;
    public Boolean F7;
    public Boolean F8;

    public String Notes;
    public Boolean Block;
    public long GlobalId;
    public Customer Customer;
    public Set<ActivationData> ActivatedMachines;
    public Boolean TrialActivation;
    public int MaxNoOfMachines;
    public String AllowedMachines;
    public Set<DataObject> DataObjects;
    public LocalDateTime SignDate;
    public String Signature;
}
