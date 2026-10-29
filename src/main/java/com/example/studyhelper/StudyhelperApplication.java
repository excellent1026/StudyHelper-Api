package com.example.studyhelper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.studyhelper.mapper")
public class StudyhelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyhelperApplication.class, args);
    }
}