package com.jw.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: jiawei
 * @Date: 2020/5/28
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EurekaApplication.class, args);
        Resource resource = (Resource) new ClassPathResource("application.yml");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        String host = props.getProperty("server.post");
        String zone = props.getProperty("eureka.client.service-url.default-zone");
        System.out.println(host);
        System.out.println(zone);

    }
}
