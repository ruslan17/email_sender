package com.application.task.service;

import com.application.task.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    private static final String CRON = "*/10 * * * * *";

    private LocalDate DATE = LocalDate.now();

    private final UserRepositoryService userService;

    private final EmailService emailService;

    @Autowired
    public SchedulerService(EmailService emailService, UserRepositoryService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        if (userService.getUsers() != null && userService.getUsers().size() != 0) {
            List<User> list = userService.getUsers();
            System.out.println(list);
            list.forEach(user -> {
                if (DATE.getMonth() == user.getBirthday().getMonth() && DATE.getDayOfMonth() == user.getBirthday().getDayOfMonth()) {
                    try {
                        StringBuffer message = new StringBuffer();
                        message.append("Happy Birthday dear ")
                                .append(user.getName())
                                .append("!");
                        emailService.send(user.getEmail(), "Happy Birthday!", message.toString());
                        LOG.info("Email have been sent. User id: {}, Date: {}", user.getId(), DATE);
                    } catch (Exception e) {
                        LOG.error("Email can't be sent.User's id: {}, Error: {}", user.getId(), e.getMessage());
                    }
                }
            });
        }
    }

}