package com.mac.pip4.another_attempt.lab4.repository;

import com.mac.pip4.another_attempt.lab4.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
