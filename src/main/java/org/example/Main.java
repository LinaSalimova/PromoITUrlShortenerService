package org.example;

import org.example.notification.ConsoleUserNotifier;
import org.example.notification.UserNotifier;
import org.example.repository.InMemoryUrlManager;
import org.example.repository.UrlManager;
import org.example.service.UrlShortenerService;
import org.example.ui.ConsoleInterface;
import org.example.user.InMemoryUserManager;
import org.example.user.UserManager;
import org.example.util.BaseUrlShortener;
import org.example.util.UrlShortener;

/**
 * Главный класс для демонстрации работы сервиса сокращения ссылок.
 */
public class Main {
     /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        UrlShortener shortener = new BaseUrlShortener();
        UrlManager urlManager = new InMemoryUrlManager();
        UserNotifier notifier = new ConsoleUserNotifier();
        UserManager userManager = new InMemoryUserManager();
        UrlShortenerService service = new UrlShortenerService(shortener, urlManager, notifier, userManager);

        ConsoleInterface consoleInterface = new ConsoleInterface(service);
        consoleInterface.run();
    }
}
