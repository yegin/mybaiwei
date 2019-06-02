package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.Hr;
import com.sxt.baiwei.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String s);

    List<Role> getRolesByHrId(Integer hrid);

    List<Hr> getAllHrs(Integer id);

//    聊天记录的所有hr
    List<Hr> getAllHr(@Param("currentId") Integer currentId);
}