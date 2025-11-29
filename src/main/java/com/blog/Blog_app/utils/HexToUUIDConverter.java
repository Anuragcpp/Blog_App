package com.blog.Blog_app.utils;

import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

@Component
public class HexToUUIDConverter {

    // Remove "0x" prefix if present
    public static String cleanHex(String hex) {
        if (hex == null) return null;
        return hex.startsWith("0x") || hex.startsWith("0X")
                ? hex.substring(2)
                : hex;
    }

    // Convert Hex String → UUID
    public static UUID hexToUUID(String hex) {
        hex = cleanHex(hex);

        if (hex.length() != 32) {
            throw new IllegalArgumentException("Hex string must be 32 characters to convert to UUID");
        }

        byte[] bytes = new byte[16];

        for (int i = 0; i < 16; i++) {
            int index = i * 2;
            bytes[i] = (byte) Integer.parseInt(hex.substring(index, index + 2), 16);
        }

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();

        return new UUID(high, low);
    }

}
