package com.jw.cloud.feign;

import com.jw.cloud.entry.AcMenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Auther: jiawei
 * @Date: 2020/6/4
 * @Description:
 */

@FeignClient(name = "USER-SERVER")
public interface UserFeignClient {
    @GetMapping("/user/list")
    List<AcMenu> getUserList();
}
