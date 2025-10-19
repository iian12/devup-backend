package com.example.devup_backend.global.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TsidBase62Util {

    private final String alphabet;
    private final int BASE = 62;

    public TsidBase62Util(@Value("${base62.secret}") String alphabet) {
        this.alphabet = alphabet;
    }

    public String encode(long tsid) {
        StringBuilder sb = new StringBuilder();

        while (tsid > 0) {
            sb.append(alphabet.charAt((int) (tsid % BASE)));
            tsid /= BASE;
        }

        return sb.reverse().toString();
    }

    public long decode(String base62) {
        if (base62 == null || base62.isEmpty()) return 0;
        long result = 0;
        for (char c : base62.toCharArray()) {
            result = result * BASE + alphabet.indexOf(c);
        }

        return result;
    }
}
