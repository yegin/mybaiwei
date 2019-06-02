package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.Salary;

import java.util.List;

public interface SalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    List<Salary> getTotalSalarys();

    boolean updateSalary(Integer eid, Integer sid);
}