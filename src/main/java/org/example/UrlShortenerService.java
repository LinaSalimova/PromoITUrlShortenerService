package org.example;

import java.awt.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
/**
 * Основной сервис для управления сокращенными ссылками.
 */
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
    
    /**
     * Создает новую короткую ссылку.
     *
     * @param longUrl длинный URL для сокращения
     * @param userId идентификатор пользователя
     * @param maxClicks максимальное количество переходов по ссылке
     * @return короткая ссылка
     */

    public String createShortUrl(String longUrl, int maxClicks) {
        UUID userId = userManager.createUser();
        String shortUrl = urlShortener.shorten(longUrl, userId);
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);
        UrlInfo urlInfo = new UrlInfo(longUrl, shortUrl, userId, maxClicks, expirationDate);
        urlManager.addUrl(shortUrl, urlInfo);
        userManager.addUrlToUser(userId, shortUrl);
        return shortUrl;
    }
    /**
     * Выполняет переход по короткой ссылке.
     *
     * @param shortUrl короткая ссылка
     * @throws Exception если возникла ошибка при переходе
     */

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
    /**
     * Удаляет все истекшие ссылки и уведомляет пользователей.
     */
    public void cleanExpiredUrls() {
        Map<String, UrlInfo> allUrls = urlManager.getAllUrls();
        for (Map.Entry<String, UrlInfo> entry : allUrls.entrySet()) {
            if (entry.getValue().getExpirationDate().isBefore(LocalDateTime.now())) {
                userNotifier.notifyLinkExpired(entry.getValue().getUserId(), entry.getKey());
            }
        }
        urlManager.removeExpiredUrls();
    }
     /**
     * Получает информацию о ссылке по её короткому варианту.
     *
     * @param shortUrl короткая ссылка
     * @return информация о ссылке или null, если ссылка не найдена
     */
    public UrlInfo getUrlInfo(String shortUrl) {
        return urlManager.getUrlInfo(shortUrl);
    }
}

