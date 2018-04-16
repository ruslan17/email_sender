package com.application.task.service;

import com.application.task.model.User;

import java.util.List;

public interface UserRepositoryService {

    List<User> getUsersByBirthday(int month, int day);
}
