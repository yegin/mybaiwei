package com.sxt.baiwei.controller.salary;

import com.sxt.baiwei.bean.RespBean;
import com.sxt.baiwei.bean.RespBeanPage;
import com.sxt.baiwei.bean.Salary;
import com.sxt.baiwei.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalaryController {

    @Autowired
    SalaryService salaryService;


    /**
     * 查询全部分页数据，设置工资账套
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public RespBeanPage getAllEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        return salaryService.getAllEmployeeByPage(page, size);

    }

    @GetMapping("/getsalarys")
    public List<Salary> getTotalSalarys() {
        return salaryService.getTotalSalarys();
    }


    /**
     * 更新工资账套变更后的数据
     * @param eid
     * @param sid
     * @return
     */
    @PutMapping("/")
    public RespBean updateSalary(Integer eid,Integer sid) {
        if (salaryService.updateSalary(eid, sid)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
//        test git
    }




}
