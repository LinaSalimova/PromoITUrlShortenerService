package org.example;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserManager implements UserManager {
    private Map<UUID, Set<String>> userUrls = new ConcurrentHashMap<>();

    @Override
    public UUID createUser() {
        UUID userId = UUID.randomUUID();
        userUrls.put(userId, new HashSet<>());
        return userId;
    }

    @Override
    public boolean isOwner(UUID userId, String shortUrl) {
        return userUrls.containsKey(userId) && userUrls.get(userId).contains(shortUrl);
    }

    @Override
    public void addUrlToUser(UUID userId, String shortUrl) {
        userUrls.computeIfAbsent(userId, k -> new HashSet<>()).add(shortUrl);
    }
}
