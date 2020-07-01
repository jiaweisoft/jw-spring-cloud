package com.jw.cloud2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@tk.mybatis.spring.annotation.MapperScan({"com.jw.cloud2.dao"})
@SpringBootApplication(scanBasePackages = {"com.jw.cloud2.service",
        "com.jw.cloud2.controller", "com.jw.cloud2.dao", "com.jw.cloud2.config", "com.jw.cloud2.component"})
@EnableTransactionManagement
@EnableEurekaClient
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

}
