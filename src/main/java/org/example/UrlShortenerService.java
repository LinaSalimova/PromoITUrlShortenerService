package org.example;

import java.awt.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class UrlShortenerService {
    private final UrlShortener urlShortener;
    private final UrlManager urlManager;
    private final UserNotifier userNotifier;
    private final UserManager userManager;

    public UrlShortenerService(UrlShortener urlShortener, UrlManager urlManager, UserNotifier userNotifier, UserManager userManager) {
        this.urlShortener = urlShortener;
        this.urlManager = urlManager;
        this.userNotifier = userNotifier;
        this.userManager = userManager;
    }

    public String createShortUrl(String longUrl, int maxClicks) {
        UUID userId = userManager.createUser();
        String shortUrl = urlShortener.shorten(longUrl, userId);
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);
        UrlInfo urlInfo = new UrlInfo(longUrl, shortUrl, userId, maxClicks, expirationDate);
        urlManager.addUrl(shortUrl, urlInfo);
        userManager.addUrlToUser(userId, shortUrl);
        return shortUrl;
    }

    public void redirectToLongUrl(String shortUrl) throws Exception {
        UrlInfo info = urlManager.getUrlInfo(shortUrl);
        if (info != null && info.isActive()) {
            urlManager.incrementClicks(shortUrl);
            if (info.getClicks() >= info.getMaxClicks()) {
                info.setActive(false);
                userNotifier.notifyLinkLimitReached(info.getUserId(), shortUrl);
            }
            Desktop.getDesktop().browse(new URI(info.getLongUrl()));
        } else {
            System.out.println("Link is not active or does not exist.");
        }
    }

    public void cleanExpiredUrls() {
        Map<String, UrlInfo> allUrls = urlManager.getAllUrls();
        for (Map.Entry<String, UrlInfo> entry : allUrls.entrySet()) {
            if (entry.getValue().getExpirationDate().isBefore(LocalDateTime.now())) {
                userNotifier.notifyLinkExpired(entry.getValue().getUserId(), entry.getKey());
            }
        }
        urlManager.removeExpiredUrls();
    }

    public UrlInfo getUrlInfo(String shortUrl) {
        return urlManager.getUrlInfo(shortUrl);
    }
}

