package com.sxt.baiwei.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxt.baiwei.bean.RespBean;
import com.sxt.baiwei.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    HrService hrService;

    @Autowired
    MyDecisionManager myDecisionManager;

    @Autowired
    MySecurityFilter mySecurityFilter;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                authorizeRequests.anyRequest()
//                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                       o.setSecurityMetadataSource(mySecurityFilter);
                       o.setAccessDecisionManager(myDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin()
                //登录页面，用户未登录时自动跳转到的页面
                .loginPage("/login")
//                登录处理接口
                .loginProcessingUrl("/dologin")
//                修改用户名参数
                .usernameParameter("username")
//                修改密码参数名
                .passwordParameter("password")
//                登录成功后要跳转的页面
                .successForwardUrl("/hello")
//                登录成功后的处理器，可以返回json数据
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        ObjectMapper om = new ObjectMapper();
                        String s = om.writeValueAsString(RespBean.ok("登录成功", authentication.getPrincipal()));
                        writer.write(s);
                        writer.flush();
                        writer.close();

                    }
                })
//                登陆失败处理器
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse,
                                                        AuthenticationException e) throws IOException, ServletException {
                        RespBean error = RespBean.error("登陆失败");
                        if (e instanceof BadCredentialsException) {
                            error.setMsg("用户名或密码错误");
                        } else if (e instanceof DisabledException) {
                            error.setMsg("账户被禁用，登陆失败");

                        } else {
                            error.setMsg("登陆失败");
                        }
                        httpServletResponse.setContentType("application/json;charset=UTF-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(error));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                RespBean error = RespBean.error("登陆失败");
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(error));
                writer.flush();
                writer.close();
            }
        });

    }


}
