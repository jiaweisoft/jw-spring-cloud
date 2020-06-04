package com.jw.cloud.controller;

import com.jw.cloud.entry.AcMenu;
import com.jw.cloud.service.UserService;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: jiawei
 * @Date: 2020/5/27
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;
    @GetMapping("/list")
    public List<AcMenu> list() {
        eurekaClient.getNextServerFromEureka("USER-SERVER",false);
        EurekaClientConfig eurekaClientConfig =eurekaClient.getEurekaClientConfig();
        return userService.selectList();
    }
}
