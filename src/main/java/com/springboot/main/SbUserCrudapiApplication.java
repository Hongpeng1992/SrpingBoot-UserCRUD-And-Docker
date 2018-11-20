package com.springboot.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springboot.user.RegisterU;
import com.springboot.user.UserRepository;
import com.springboot.user.UserResource;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserResource.class)
@EntityScan(basePackageClasses = RegisterU.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SbUserCrudapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbUserCrudapiApplication.class, args);
	}
}
