package org.example;


import java.util.UUID;

public interface UserManager {
    UUID createUser();
    boolean isOwner(UUID userId, String shortUrl);
    void addUrlToUser(UUID userId, String shortUrl);
}