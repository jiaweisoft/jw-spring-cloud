package com.jw.cloud2.service.impl;

import com.jw.cloud2.dao.AcMenuMapper;
import com.jw.cloud2.entry.AcMenu;
import com.jw.cloud2.service.UserService;
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