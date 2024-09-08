package com.dev.blog;

import com.dev.blog.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class BlogApplication {
	@Autowired
	UserRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> repository.findByEmail(username)
				.orElseThrow();
	}

}
