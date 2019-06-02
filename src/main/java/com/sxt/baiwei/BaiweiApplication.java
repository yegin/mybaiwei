package com.sxt.baiwei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sxt.baiwei.mapper")
public class BaiweiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiweiApplication.class, args);
    }

}
