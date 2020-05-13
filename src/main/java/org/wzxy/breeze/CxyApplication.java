package org.wzxy.breeze;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("org.wzxy.breeze.mapper") //扫描mapper接口位置
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class CxyApplication {
    public static void main(String[] args) {

        SpringApplication.run(CxyApplication.class, args);

    }
}
