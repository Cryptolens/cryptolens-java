package io.cryptolens.licensing;

import java.time.LocalDateTime;

/**
 * This is the structure of each entry that will be returned by GetActivatedMachines.
 */
public class ActivationData {

    public String Mid;
    public String Ip;
    public LocalDateTime Time;
}
