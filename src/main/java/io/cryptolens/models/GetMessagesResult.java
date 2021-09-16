package io.cryptolens.models;

import com.google.gson.annotations.SerializedName;
import io.cryptolens.internal.BasicResult;

import java.util.List;

public class GetMessagesResult extends BasicResult {

    @SerializedName(value = "messages", alternate = {"Messages"})
    public List<MessageObject> Messages;
}
