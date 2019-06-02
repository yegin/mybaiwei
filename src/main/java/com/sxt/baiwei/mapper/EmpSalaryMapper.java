package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.EmpSalary;
import org.apache.ibatis.annotations.Param;

public interface EmpSalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmpSalary record);

    int insertSelective(EmpSalary record);

    EmpSalary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmpSalary record);

    int updateByPrimaryKey(EmpSalary record);

    void delteSalaryByeid(@Param("eid") Integer eid);
}