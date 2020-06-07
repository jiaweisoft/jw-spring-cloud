package com.jw.cloud.feign;

import com.jw.cloud.entry.AcMenu;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: jiawei
 * @Date: 2020/6/5
 * @Description:
 */
@Component
public class UserFeignFailBack implements UserFeignClient {
    @Override
    public List<AcMenu> getUserList() {
        List<AcMenu> acMenuList = new ArrayList<>();
        AcMenu acMenu = new AcMenu();
        acMenu.setId(1212l);
        acMenuList.add(acMenu);
        return acMenuList;
    }
}
