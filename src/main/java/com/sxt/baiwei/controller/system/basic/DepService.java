package com.sxt.baiwei.controller.system.basic;

import com.sxt.baiwei.bean.Department;
import com.sxt.baiwei.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepart() {
        return departmentMapper.getAllDepart(-1);
    }

    public void deleDept(Department department) {
        departmentMapper.deleDept(department);
    }

    public void addDept(Department department) {
        department.setEnabled(true);
        departmentMapper.addDept(department);
    }

    public List<Department> getAllDepts() {
        return departmentMapper.getAllDepts();
    }
}
