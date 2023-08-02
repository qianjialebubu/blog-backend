package com.example.blog2;


//import org.apache.shiro.spring.boot.autoconfigure.ShiroBeanAutoConfiguration;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.web.bind.annotation.CrossOrigin;



@CrossOrigin
@SpringBootApplication
//@EnableAutoConfiguration(exclude = ShiroBeanAutoConfiguration.class)
public class Blog2Application {

    public static void main(String[] args) {
        SpringApplication.run(Blog2Application.class, args);
    }

}
