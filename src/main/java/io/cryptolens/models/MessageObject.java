package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;

public class MessageObject {

    @SerializedName(value = "id", alternate = {"Id"})
    public int Id;

    @SerializedName(value = "content", alternate = {"Content"})
    public String Content;

    @SerializedName(value = "created", alternate = {"Created"})
    public long Created;

    @SerializedName(value = "channel", alternate = {"Channel"})
    public String Channel;
}
