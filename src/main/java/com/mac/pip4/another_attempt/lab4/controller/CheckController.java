package com.mac.pip4.another_attempt.lab4.controller;

import com.mac.pip4.another_attempt.lab4.model.Check;
import com.mac.pip4.another_attempt.lab4.repository.CheckRepository;
import com.mac.pip4.another_attempt.lab4.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("result")
public class CheckController {

    private final CheckRepository checkRepository;
    private final UserRepository userRepo;

    @Autowired
    public CheckController(UserRepository userRepo, CheckRepository checkRepository) {
        this.userRepo = userRepo;
        this.checkRepository = checkRepository;
    }


    @GetMapping
    public List<Check> list() {
        return (List<Check>) checkRepository.findAll();
    }

    @GetMapping("{check_id}")
    public Check getOne(@PathVariable("check_id") Long id) {
        return checkRepository.findById(id).orElse(null);
    }

    @PostMapping
    @Transactional
    public Check check(@RequestBody Check check) {
        if (validate(check.getX(), check.getY(), check.getR())) {
            check.setIsInArea(checkArea(check.getX(), check.getY(), check.getR()));
            Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (o instanceof UserDetails) {
                check.setShooter(userRepo.findByUsername(((UserDetails) o).getUsername()).getUsername());
            } else
                check.setShooter("ops");

            return checkRepository.save(check);
        } else
            return null;
    }

    @PutMapping("{check_id}")
    @Transactional
    public Check update(@PathVariable("check_id") Long id, @RequestBody Check result) {
        Check oldResult = checkRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(result, oldResult, "id");
        return checkRepository.save(oldResult);
    }

    @DeleteMapping("{check_id}")
    @Transactional
    public void delete(@PathVariable("check_id") Long id) {
        checkRepository.deleteById(id);
    }

    private boolean validate(double x, double y, double r) {
        return ((!(x < -3.)) && (!(x > 5.))) &&
            ((!(y < -5.)) && (!(y > 5.))) &&
            ((!(r < -3.)) && (!(r > 5.)));
    }

    private boolean checkArea(double x, double y, double r) {
        if (r <= 0) return false;

        if (x <= 0) {
            if (y <= 0 && (y >= -x - r / 2)) {
                return true;
            } else return y >= 0 && (x * x + y * y <= r * r);
        } else return y <= 0 && y >= -r / 2 && x <= r;
    }

}
