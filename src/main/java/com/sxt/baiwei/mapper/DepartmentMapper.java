package com.sxt.baiwei.mapper;

import com.sxt.baiwei.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDepart(Integer pid);

    void deleDept(Department department);

    void addDept(Department department);

    List<Department> getAllDepts();
}