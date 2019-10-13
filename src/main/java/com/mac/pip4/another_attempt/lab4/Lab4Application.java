package com.mac.pip4.another_attempt.lab4;

import com.mac.pip4.another_attempt.lab4.model.User;
import com.mac.pip4.another_attempt.lab4.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Lab4Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Lab4Application.class, args);

		//#for tests on localhost
		/*context.getBean(UserRepository.class).save(
				new User("admin",
						context.getBean(BCryptPasswordEncoder.class).
								encode("admin")));
		*/
	}

}
// Main-Class: com.mac.pip4.another_attempt.lab4.Lab4Application