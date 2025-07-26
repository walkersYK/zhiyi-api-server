package com.yunjin.api_backed;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yunjin.api_backed.mapper")
@SpringBootApplication
public class ApiBackedApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBackedApplication.class, args);
    }

}
