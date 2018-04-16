package com.application.task.service;

import com.application.task.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<User> list = userService.getUsersByBirthday(month, day);
        if (!list.isEmpty()) {
            list.forEach(user -> {
                try {
                    String message = "Happy Birthday dear " + user.getName() + "!";
                    emailService.send(user.getEmail(), "Happy Birthday!", message);
                    log.info("Email have been sent. User id: {}, Date: {}", user.getId(), date);
                } catch (Exception e) {
                    log.error("Email can't be sent.User's id: {}, Error: {}", user.getId(), e.getMessage());
                    log.error("Email can't be sent", e);
                }
            });
        }
    }

}