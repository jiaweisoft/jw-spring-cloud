package com.jw.cloud2.dao;

import com.jw.cloud2.entry.AcMenu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface AcMenuMapper extends Mapper<AcMenu> {

    @Select(" <script> "
            + " select * from ac_menu "
            + " </script> ")
    List<AcMenu> selectList();
}