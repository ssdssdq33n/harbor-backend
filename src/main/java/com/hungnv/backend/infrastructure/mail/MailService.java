package com.hungnv.backend.infrastructure.mail;

public interface MailService {
    void send(String to, String subject, String content);
}

