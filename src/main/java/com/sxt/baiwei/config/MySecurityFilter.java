package com.sxt.baiwei.config;

import com.sxt.baiwei.bean.Menu;
import com.sxt.baiwei.bean.Role;
import com.sxt.baiwei.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MySecurityFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;


AntPathMatcher antPathMatcher = new AntPathMatcher();

//    可以判断当前url需要哪些角色
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        1.获取当前请求地址
//        2.匹配数据库中的url
//        3.根据url找角色
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> menus = menuService.getAllURLMenus();
        for (Menu menu:menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
//                如果匹配成功，查询url的角色
                List<Role> roles = menu.getRoles();
                String[] rs = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    rs[i] = roles.get(i).getName();
                }
//                返回角色的名字
                return SecurityConfig.createList(rs);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
