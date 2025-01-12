package org.example;

import java.util.UUID;

public interface UserNotifier {
    void notifyLinkExpired(UUID userId, String shortUrl);
    void notifyLinkLimitReached(UUID userId, String shortUrl);
}