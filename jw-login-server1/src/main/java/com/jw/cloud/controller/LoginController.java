package com.jw.cloud.controller;

import com.jw.cloud.entry.AcMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/list")
    public List getUser() {
        List<AcMenu> list = restTemplate.getForObject("http://USER-SERVER/user/list", List.class);
        return list;
    }
}
