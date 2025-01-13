package org.example;
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
