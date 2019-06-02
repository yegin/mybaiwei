package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.MenuRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    List<Integer> getAllMidsByRid(Integer rid);

    Integer deleteByRid(@Param("rid") Integer id);

    Integer addCheckMenus(Integer rid, Integer[] checkedMenu);
}