package com.sxt.baiwei.service;

import com.sxt.baiwei.bean.Menu;
import com.sxt.baiwei.Utils.HrUtils;
import com.sxt.baiwei.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper mapper;

    public List<Menu> getAllMenus() {
        return mapper.getAllMenus(HrUtils.getCurrentHr().getId());
    }


    public List<Menu> getAllURLMenus() {
        return mapper.getAllURLMenus();
    }

    public List<Menu> getAllMenusForPermission() {
        return mapper.getAllMenusForPermission();
    }

    public List<Menu> getMenusByHrId() {

        return mapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
    }
}
