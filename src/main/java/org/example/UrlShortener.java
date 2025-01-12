package org.example;

import java.util.UUID;

public interface UrlShortener {
    String shorten(String longUrl, UUID userId);
}