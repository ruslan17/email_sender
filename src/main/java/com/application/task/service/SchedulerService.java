package com.application.task.service;

import com.application.task.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {

    private static final String CRON = "*/10 * * * * *";

    private final UserRepositoryService userService;

    private final EmailService emailService;

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        if (!userService.getUsers().isEmpty()) {
            LocalDate DATE = LocalDate.now();
            List<User> list = userService.getUsers();
            list.forEach(user -> {
                if (DATE.getMonth() == user.getBirthday().getMonth() && DATE.getDayOfMonth() == user.getBirthday().getDayOfMonth()) {
                    try {
                        String message = "Happy Birthday dear " + user.getName() + "!";
                        emailService.send(user.getEmail(), "Happy Birthday!", message);
                        log.info("Email have been sent. User id: {}, Date: {}", user.getId(), DATE);
                    } catch (Exception e) {
                        log.error("Email can't be sent.User's id: {}, Error: {}", user.getId(), e.getMessage());
                        log.error("Email can't be sent", e);
                    }
                }
            });
        }
    }

}