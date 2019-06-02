package com.sxt.baiwei.controller;

import com.sxt.baiwei.bean.Menu;
import com.sxt.baiwei.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/config/allmenus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

}
