package com.mac.pip4.another_attempt.lab4.repository;

import com.mac.pip4.another_attempt.lab4.model.Check;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckRepository extends CrudRepository<Check,  Long> {
    List<Check> findByShooterOrderByCheckId(String shooter);
}
