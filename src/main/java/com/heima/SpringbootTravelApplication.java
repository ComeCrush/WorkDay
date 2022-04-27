package com.heima;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.heima.travel.mapper")
public class SpringbootTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTravelApplication.class, args);
    }

}
