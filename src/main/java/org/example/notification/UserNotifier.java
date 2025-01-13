package org.example.notification;

import java.util.UUID;
/**
 * Интерфейс для уведомления пользователей о событиях, связанных с их ссылками.
 */
public interface UserNotifier {
     /**
     * Уведомляет пользователя об истечении срока действия ссылки.
     *
     * @param userId идентификатор пользователя
     * @param shortUrl короткая ссылка
     */
    void notifyLinkExpired(UUID userId, String shortUrl);
    /**
     * Уведомляет пользователя о достижении лимита переходов по ссылке.
     *
     * @param userId идентификатор пользователя
     * @param shortUrl короткая ссылка
     */
    void notifyLinkLimitReached(UUID userId, String shortUrl);
}
