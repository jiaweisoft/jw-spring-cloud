package com.jw.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: jiawei
 * @Date: 2020/6/11
 * @Description:
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${spring.test}")
    private String test;

    @GetMapping("/profile")
    public String getProfile() {
        return this.test;
    }
}
