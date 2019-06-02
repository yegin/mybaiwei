package com.sxt.baiwei.service;

import com.sxt.baiwei.bean.*;
import com.sxt.baiwei.mapper.EmpSalaryMapper;
import com.sxt.baiwei.mapper.EmployeeMapper;
import com.sxt.baiwei.mapper.EmployeeecMapper;
import com.sxt.baiwei.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaryService {

    @Autowired
    EmployeeMapper employeeeMapper;

    @Autowired
    SalaryMapper salaryMapper;

    @Autowired
    EmpSalaryMapper empSalaryMapper;

    /**
     * 插叙工资账套全部分页数据
     *
     * @param page
     * @param size
     * @return
     */
    public RespBeanPage getAllEmployeeByPage(Integer page, Integer size) {
        RespBeanPage respBeanPage = new RespBeanPage();
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
//        查询全部集合
        List<Employee> list = employeeeMapper.getAllEmployeeByPage(page, size);
        respBeanPage.setData(list);

//        查询全部数据
        Long total = employeeeMapper.getTotal();
        respBeanPage.setTotal(total);

        return respBeanPage;
    }

    /**
     * 获取所有员工的工资账套
     * @return
     */
    public List<Salary> getTotalSalarys() {
        return salaryMapper.getTotalSalarys();
    }


    /**
     * 先按照eid将工资账套删除，然后更新数据
     * @param eid
     * @param sid
     * @return
     */
    @Transactional
    public boolean updateSalary(Integer eid, Integer sid) {
        empSalaryMapper.delteSalaryByeid(eid);
        EmpSalary record = new EmpSalary();
        record.setEid(eid);
        record.setSid(sid);
        return empSalaryMapper.insertSelective(record)==1;
    }
}
