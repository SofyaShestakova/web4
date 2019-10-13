package com.mac.pip4.another_attempt.lab4.controller;

import com.mac.pip4.another_attempt.lab4.model.User;
import com.mac.pip4.another_attempt.lab4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CheckRepository checkRepository;
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;

//    @ModelAttribute("user")
//    public Optional<User> getUser() {
//        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (o instanceof UserDetails) {
//            return userRepository.findByUsername(((UserDetails) o).getUsername());
//        } else {
//            return Optional.empty();
//        }
//    }

//    @GetMapping("{username}")
//    public User getOne(@PathVariable("username") String username) {
//        return userRepo.findById(username).orElse(null);
//    }
//
//    @PostMapping
//    public String newUser(@RequestParam String username, @RequestParam String password) {
//        Optional<User> user = userRepository.findByUsername(username);
//
//        if (password == null || password.trim().isEmpty()) {
//            return
//
//
//                    "{\"error\": password field or user field is empty!}";
//        }
//        if (!user.isPresent()) {
//            userRepository.save(new User(username, passwordEncoder.encode(password)));
//            return null;
//        } else {
//            return "{\"error\": we already have user \"" + username + "\"}";
//        }
//    }
    // =============================

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @GetMapping("{username}")
    public User getOne(@PathVariable("username") String username) {
        return userRepo.findById(username).orElse(null);
    }

    @GetMapping
    public List<User> list() {
        return (List<User>) userRepo.findAll();
    }

    @PostMapping
    @Transactional
    public User create(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @PutMapping("{username}")
    @Transactional
    public User update(@PathVariable("username") String username, @RequestBody User user) {
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @DeleteMapping("{username}")
    @Transactional
    public void delete(@PathVariable("username") String username) {
        userRepo.deleteById(username);
    }

}
