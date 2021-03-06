package com.jw.cloud2.controller;

import com.jw.cloud2.component.jms.send.SendUtilCommon;
import com.jw.cloud2.entry.AcMenu;
import com.jw.cloud2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

    @Qualifier("eurekaClient")
    @GetMapping("/list")
    public List<AcMenu> list() {
        log.info(this.test);
        return userService.selectList();
    }


    @PostMapping("/sendCommon")
    public String sendCommonsendCommon(String text, String exchangeName, String queueKey) {
        sendUtilCommon.sendMessage(text, exchangeName, queueKey);
        return text;
    }
}
