package com.jw.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Auther: jiawei
 * @Date: 2020/5/28
 * @Description:
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
