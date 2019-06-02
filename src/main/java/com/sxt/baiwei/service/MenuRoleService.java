package com.sxt.baiwei.service;

import com.sxt.baiwei.bean.Role;
import com.sxt.baiwei.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuRoleService {

    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Integer> getAllMidsByRid(Integer rid) {
        return menuRoleMapper.getAllMidsByRid(rid);
    }


    @Transactional
    public Integer updateMRByRid(Role role) {
        menuRoleMapper.deleteByRid(role.getId());
        return menuRoleMapper.addCheckMenus(role.getId(), role.getCheckedMenu());
    }
}
