package io.cryptolens.models;

public class ActivatedMachine {

    /**
     * The machine code.
     * More info: https://app.cryptolens.io/docs/api/v3/model/ActivationData?modelVersion=3
     */
    public String Mid;
    public String IP;
    public long Time;

    public String FriendlyName;
    public long FloatingExpires;
}
