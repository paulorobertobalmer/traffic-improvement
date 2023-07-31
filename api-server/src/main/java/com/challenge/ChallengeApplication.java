package com.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.challenge.common.entity", "com.challenge.repository"})
@ComponentScan(basePackages = { "com.challenge.service", "com.challenge.controller" })
public class ChallengeApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChallengeApplication.class, args);
  }

}
