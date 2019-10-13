package com.mac.pip4.another_attempt.lab4.service;

import com.mac.pip4.another_attempt.lab4.model.User;
import com.mac.pip4.another_attempt.lab4.repository.UserRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    @Transactional
//    public void save(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//
//    public boolean isUserExists(String username, String password) {
//        Optional<User> user = userRepository.findByUsername(username);
//        return user.map(users -> bCryptPasswordEncoder.matches(password,user.get().getPassword())).orElse(false);
//    }
//
//
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username).orElse(null);
//    }
//}
@Service
@Primary
public class UserService implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        org.springframework.security.core.userdetails.User.UserBuilder builder;
        User user = userRepo.findById(username).orElse(null);

        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.roles("DEFAULT");

        return builder.build();
    }
}