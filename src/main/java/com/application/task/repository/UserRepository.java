package com.application.task.repository;

import com.application.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByBirthdayIsNotNullAndEmailIsNotNull();

}
