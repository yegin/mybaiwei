package com.sxt.baiwei.controller.system.basic;

import com.sxt.baiwei.bean.Department;
import com.sxt.baiwei.bean.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/dep")
public class DepartmentController {

    @Autowired
    DepService depService;

    @GetMapping("/")
    public List<Department> getAllDepart() {
        return depService.getAllDepart();
    }


//    获取全部的部门信息供添加用
    @GetMapping("/alldepts")
    public List<Department> getAllDepart2() {
        return depService.getAllDepts();
    }

    @PostMapping("/")
    public RespBean addDept(@RequestBody Department department) {
        depService.addDept(department);
        if (department.getResult() == 1) {
            return RespBean.ok("部门添加成功",department);
        }
        return RespBean.error("部门添加失败");

    }

    @DeleteMapping("/")
    public RespBean deleDept(Department department) {

        depService.deleDept(department);
        if (department.getResult() == 1) {
            return RespBean.ok("部门删除成功)");
        } else if (department.getResult() == -2) {
            return RespBean.error("部门下有子部门，删除失败)");
        } else if (department.getResult()==-1) {
            return RespBean.error("部门下有子部门，删除失败)");
        }

        return RespBean.error("删除失败)");
    }

}
