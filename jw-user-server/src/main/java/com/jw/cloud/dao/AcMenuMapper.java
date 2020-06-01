package com.jw.cloud.dao;

import com.jw.cloud.entry.AcMenu;
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