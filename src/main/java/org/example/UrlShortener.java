package org.example;

import java.util.UUID;
/**
 * Интерфейс для генерации коротких ссылок.
 */
public interface UrlShortener {
     /**
     * Создает короткую ссылку на основе длинного URL и идентификатора пользователя.
     *
     * @param longUrl длинный URL для сокращения
     * @param userId идентификатор пользователя
     * @return короткая ссылка
     */
    String shorten(String longUrl, UUID userId);
}
