package org.example;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUrlManager implements UrlManager {
    private Map<String, UrlInfo> urlDatabase = new ConcurrentHashMap<>();

    @Override
    public void addUrl(String shortUrl, UrlInfo urlInfo) {
        urlDatabase.put(shortUrl, urlInfo);
    }

    @Override
    public UrlInfo getUrlInfo(String shortUrl) {
        return urlDatabase.get(shortUrl);
    }

    @Override
    public void incrementClicks(String shortUrl) {
        UrlInfo info = urlDatabase.get(shortUrl);
        if (info != null) {
            info.incrementClicks();
            if (info.getClicks() >= info.getMaxClicks()) {
                info.setActive(false);
            }
        }
    }

    @Override
    public void removeExpiredUrls() {
        LocalDateTime now = LocalDateTime.now();
        urlDatabase.entrySet().removeIf(entry -> entry.getValue().getExpirationDate().isBefore(now));
    }

    @Override
    public Map<String, UrlInfo> getAllUrls() {
        return new ConcurrentHashMap<>(urlDatabase);
    }
}

