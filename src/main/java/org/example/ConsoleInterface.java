package org.example;
import java.util.Scanner;

import java.util.Scanner;

public class ConsoleInterface {
    private final UrlShortenerService service;
    private final Scanner scanner;

    public ConsoleInterface(UrlShortenerService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\nURL Shortener Service");
            System.out.println("1. Create short URL");
            System.out.println("2. Redirect to long URL");
            System.out.println("3. Clean expired URLs");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createShortUrl();
                    break;
                case 2:
                    redirectToLongUrl();
                    break;
                case 3:
                    service.cleanExpiredUrls();
                    System.out.println("Expired URLs cleaned.");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createShortUrl() {
        System.out.print("Enter long URL: ");
        String longUrl = scanner.nextLine();
        System.out.print("Enter max clicks: ");
        int maxClicks = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String shortUrl = service.createShortUrl(longUrl, maxClicks);
        System.out.println("Short URL created: " + shortUrl);
    }

    private void redirectToLongUrl() {
        System.out.print("Enter short URL: ");
        String shortUrl = scanner.nextLine();
        try {
            service.redirectToLongUrl(shortUrl);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
