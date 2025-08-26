package io.cryptolens.legacy;

import java.util.Base64;

public class Shared {

    public static byte[] defaultBase64Decoder(String str) {
        return Base64.getDecoder().decode(str); // standard Base64
    }
}
