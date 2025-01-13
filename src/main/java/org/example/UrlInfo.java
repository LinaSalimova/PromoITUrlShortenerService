package org.example;

import java.time.LocalDateTime;
import java.util.UUID;
/**
 * Класс, представляющий информацию о сокращенной ссылке.
 */
public class UrlInfo {
    private final String longUrl;
    private final String shortUrl;
    private final UUID userId;
    private final int maxClicks;
    private final LocalDateTime expirationDate;
    private int clicks;
    private boolean active;

    public UrlInfo(String longUrl, String shortUrl, UUID userId, int maxClicks, LocalDateTime expirationDate) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.userId = userId;
        this.maxClicks = maxClicks;
        this.expirationDate = expirationDate;
        this.clicks = 0;
        this.active = true;
    }

    // Геттеры
    public String getLongUrl() { return longUrl; }
    public String getShortUrl() { return shortUrl; }
    public UUID getUserId() { return userId; }
    public int getMaxClicks() { return maxClicks; }
    public LocalDateTime getExpirationDate() { return expirationDate; }
    public int getClicks() { return clicks; }
    public boolean isActive() { return active; }

    // Сеттер для active
    public void setActive(boolean active) { this.active = active; }
    /**
     * Увеличивает счетчик переходов по ссылке.
     */
    public void incrementClicks() {
        clicks++;
    }
}


