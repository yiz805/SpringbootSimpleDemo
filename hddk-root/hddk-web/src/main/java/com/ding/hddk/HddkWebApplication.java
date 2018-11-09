package com.ding.hddk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ding.hddk.dao")
public class HddkWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HddkWebApplication.class, args);
    }
}
