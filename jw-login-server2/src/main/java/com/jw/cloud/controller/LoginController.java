package com.jw.cloud.controller;

import com.jw.cloud.entry.AcMenu;
import com.jw.cloud.feign.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jiawei
 * @Date: 2020/5/28
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserFeignClient userFeignClient;

    @GetMapping("/list")
    public List getUser() {
        List<AcMenu> list = userFeignClient.getUserList();
        return list;
    }

}
