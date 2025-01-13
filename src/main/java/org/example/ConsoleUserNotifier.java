package org.example;

import java.util.UUID;
/**
 * Реализация интерфейса UserNotifier, выводящая уведомления в консоль.
 */
public class ConsoleUserNotifier implements UserNotifier {
    @Override
    public void notifyLinkExpired(UUID userId, String shortUrl) {
        System.out.println("User " + userId + ": Link " + shortUrl + " has expired.");
    }

    @Override
    public void notifyLinkLimitReached(UUID userId, String shortUrl) {
        System.out.println("User " + userId + ": Link " + shortUrl + " has reached its click limit.");
    }
}

