package com.jw.cloud.controller;

import com.jw.cloud.component.jms.send.SendUtilCommon;
import com.jw.cloud.component.jms.send.SendUtilCommon2;
import com.jw.cloud.entry.AcMenu;
import com.jw.cloud.service.UserService;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RefreshScope
@Slf4j
public class UserController {
    @Value("${spring.test}")
    private String test;

    @Autowired
    private UserService userService;
    @Autowired
    private SendUtilCommon sendUtilCommon;

    @Autowired
    private SendUtilCommon2 sendUtilCommon2;
    @Qualifier("eurekaClient")
    @GetMapping("/list")
    public List<AcMenu> list() {
        log.info(this.test);
        return userService.selectList();
    }


    @PostMapping("/sendMq")
    public String sentMq(String text, String exchangeName, String queueKey) {
        sendUtilCommon.sendMessage(text, exchangeName, queueKey);
        return text;
    }
    @PostMapping("/sendMq2")
    public String sentMq2(String text, String exchangeName, String queueKey) {
        sendUtilCommon2.sendMessage(text, exchangeName, queueKey);
        return text;
    }
}
