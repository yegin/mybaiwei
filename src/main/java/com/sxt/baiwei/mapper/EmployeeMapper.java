package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.Employee;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getAllEmployeeByPage(Integer page, Integer size);

    long getTotal();
}