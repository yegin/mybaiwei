package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getAllMenus(Integer hrId);

    List<Menu> getAllURLMenus();

    List<Menu> getAllMenusForPermission();


    List<Menu> getMenusByHrId(Integer id);
}