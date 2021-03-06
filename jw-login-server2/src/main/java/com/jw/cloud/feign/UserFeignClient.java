package com.jw.cloud.feign;

import com.jw.cloud.entry.AcMenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Auther: jiawei
 * @Date: 2020/6/4
 * @Description:
 */

@FeignClient(name = "USER-SERVER", path = "/user", fallback = UserFeignFailBack.class)
public interface UserFeignClient {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<AcMenu> getUserList();
}
