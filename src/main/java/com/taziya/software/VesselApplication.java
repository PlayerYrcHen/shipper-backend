package com.taziya.software;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.taziya.software.mapper")
public class VesselApplication {
    public static void main(String[] args) {
        SpringApplication.run(VesselApplication.class, args);
    }
}
