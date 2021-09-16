package io.cryptolens.models;

public class GetMessagesModel extends RequestModel {

    /**
     * Specifies the channel, whose messages you would like to retrieve.
     * If not set, messages from all channels will be returned.
     */
    public String Channel;

    /**
     * Allows you to retrieve only those messages that were created after
     * a certain Time (strictly greater than), eg. the last time you contacted
     * the server. The format is unix timestamp. If no time is specified, all
     * messages will be returned.
     */
    public long Time;


    public GetMessagesModel () {}

    public GetMessagesModel(String channel) {
        Channel = channel;
    }

    public GetMessagesModel(String channel, long time) {
        Channel = channel;
        Time = time;
    }

    public GetMessagesModel(long time) {
        Time = time;
    }
}
