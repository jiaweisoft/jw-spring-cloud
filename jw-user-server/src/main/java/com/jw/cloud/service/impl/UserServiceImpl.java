package com.jw.cloud.service.impl;

import com.jw.cloud.dao.AcMenuMapper;
import com.jw.cloud.entry.AcMenu;
import com.jw.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: jiawei
 * @Date: 2020/5/27
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AcMenuMapper acMenuMapper;

    public List<AcMenu> selectList(){
       return acMenuMapper.selectList();
    }
}