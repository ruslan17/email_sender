package com.application.task.service.impl;

import com.application.task.model.User;
import com.application.task.repository.UserRepository;
import com.application.task.service.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepository repository;

    @Override
    public List<User> getUsersByBirthday(int month, int day) {
        return repository.findByMatchMonthAndMatchDay(month, day);
    }

}
