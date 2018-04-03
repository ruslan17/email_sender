package com.application.task.service.impl;

import com.application.task.model.User;
import com.application.task.repository.UserRepository;
import com.application.task.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepository repository;

    @Autowired
    public UserRepositoryServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getUsers() {
        return repository.findAllByBirthdayIsNotNullAndEmailIsNotNull();
    }

}
