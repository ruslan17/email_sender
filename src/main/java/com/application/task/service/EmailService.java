package com.application.task.service;

public interface EmailService {
    void send(String to, String title, String body);
}
