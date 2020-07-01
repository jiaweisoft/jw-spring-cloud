package com.jw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@tk.mybatis.spring.annotation.MapperScan({"com.jw.cloud.dao"})
@SpringBootApplication(scanBasePackages = {"com.jw.cloud.service",
        "com.jw.cloud.controller", "com.jw.cloud.dao", "com.jw.cloud.config", "com.jw.cloud.component"})
@EnableTransactionManagement
@EnableEurekaClient
public class UserServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }

}
