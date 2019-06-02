package com.sxt.baiwei.service;

import com.sxt.baiwei.bean.Hr;
import com.sxt.baiwei.Utils.HrUtils;
import com.sxt.baiwei.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//       根据用户名查找用户信息
        Hr hr=hrMapper.loadUserByUsername(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
//        根据用户id查询角色，通过commaSeparatedStringToAuthorityLis将角色封装为authority对象
         hr.setRoles(hrMapper.getRolesByHrId(hr.getId()));
        return hr;
    }


    public List<Hr> getAllHrs() {

        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId());
    }


    public List<Hr> getAllHr() {
        return hrMapper.getAllHr(null);
    }

    public List<Hr> getAllHrExceptAdmin() {
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId());
    }
}
