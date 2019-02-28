package io.cryptolens.legacy;

import org.apache.commons.codec.binary.Base64;

public class Shared {

    public static byte[] defaultBase64Decoder(String str) {

        Base64 decoder = new Base64();
        return decoder.decode(str.getBytes());
    }
}
