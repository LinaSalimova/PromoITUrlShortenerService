package org.example;

import java.util.Map;
/**
 * Интерфейс для управления сокращенными ссылками.
 */
public interface UrlManager {
    /**
     * Добавляет новую сокращенную ссылку в базу данных.
     *
     * @param shortUrl короткая ссылка
     * @param urlInfo информация о ссылке
     */
    void addUrl(String shortUrl, UrlInfo urlInfo);
     /**
     * Получает информацию о ссылке по её короткому варианту.
     *
     * @param shortUrl короткая ссылка
     * @return информация о ссылке или null, если ссылка не найдена
     */
    UrlInfo getUrlInfo(String shortUrl);
    /**
     * Увеличивает счетчик переходов по ссылке.
     *
     * @param shortUrl короткая ссылка
     */
    void incrementClicks(String shortUrl);
        /**
     * Удаляет все истекшие ссылки.
     */
    void removeExpiredUrls();
    /**
     * Возвращает все сохраненные ссылки.
     *
     * @return карта всех сохраненных ссылок
     */
    Map<String, UrlInfo> getAllUrls();
}
