package org.example;

import java.util.UUID;

public class BaseUrlShortener implements UrlShortener {
        private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private static final int BASE = ALPHABET.length();

    @Override
    public String shorten(String longUrl, UUID userId) {
            String uniqueId = userId.toString() + System.currentTimeMillis();
            int hash = Math.abs(uniqueId.hashCode());
            StringBuilder shortUrl = new StringBuilder();

            while (hash > 0) {
                shortUrl.append(ALPHABET.charAt(hash % BASE));
                hash = hash / BASE;
            }

            return "clck.ru/" + shortUrl.reverse().toString();
        }
    }
