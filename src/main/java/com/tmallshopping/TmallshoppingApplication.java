package com.tmallshopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.tmallshopping.mapper")
public class TmallshoppingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TmallshoppingApplication.class, args);
    }

}
