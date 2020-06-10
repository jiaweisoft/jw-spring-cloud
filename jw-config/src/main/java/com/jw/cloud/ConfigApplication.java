package com.jw.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @Auther: jiawei
 * @Date: 2020/5/28
 * @Description:
 */
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
