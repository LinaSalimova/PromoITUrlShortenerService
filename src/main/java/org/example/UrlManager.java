package org.example;

import java.util.Map;

public interface UrlManager {
    void addUrl(String shortUrl, UrlInfo urlInfo);
    UrlInfo getUrlInfo(String shortUrl);
    void incrementClicks(String shortUrl);
    void removeExpiredUrls();
    Map<String, UrlInfo> getAllUrls();
}
