package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.Employee;
import com.sxt.baiwei.bean.Employeeec;
import com.sxt.baiwei.bean.RespBeanPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeecMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employeeec record);

    int insertSelective(Employeeec record);

    Employeeec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employeeec record);

    int updateByPrimaryKey(Employeeec record);



}