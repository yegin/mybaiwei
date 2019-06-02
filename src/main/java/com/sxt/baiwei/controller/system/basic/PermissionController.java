package com.sxt.baiwei.controller.system.basic;

import com.sxt.baiwei.bean.Menu;
import com.sxt.baiwei.bean.RespBean;
import com.sxt.baiwei.bean.Role;
import com.sxt.baiwei.mapper.MenuRoleMapper;
import com.sxt.baiwei.service.MenuRoleService;
import com.sxt.baiwei.service.MenuService;
import com.sxt.baiwei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRoleService menuRoleService;


    @GetMapping("/allroles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * 查询三级菜单所有的明细
     * @return
     */
    @GetMapping("/allmenus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenusForPermission();
    }


    @GetMapping("/{rid}")
    public List<Integer> getAllMidsByRid(@PathVariable Integer rid) {
        return menuRoleService.getAllMidsByRid(rid);

    }


    @PutMapping("/")
    public RespBean updateMRByRid(@RequestBody Role role) {
        if (menuRoleService.updateMRByRid(role)>0){
            return RespBean.ok("成功");

        }
        return RespBean.error(("失败"));
}
}


