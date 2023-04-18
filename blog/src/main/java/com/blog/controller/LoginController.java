package com.blog.controller;

import com.blog.domain.SysUser;
import com.blog.mapper.SysMenuMapper;
import com.blog.service.ISysUserService;
import com.blog.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sys-user/")
public class LoginController {

    @Autowired
    private ISysUserService sysUserService;
    @Resource
    private SysMenuMapper sysMenuMapper;

    @PostMapping("login")
    public ResponseResult<SysUser> login(@RequestBody SysUser sysUser){

        return sysUserService.login(sysUser);
    }

    @RequestMapping("logout")
    public ResponseResult logout (){
        return sysUserService.logout();
    }

    @RequestMapping("hello")
    @PreAuthorize("hasAuthority('admin')")
    public String hello(){
        List<String> list = sysMenuMapper.selectPermsByUserId(1L);
        return list.get(0);
    }

    @RequestMapping("test")
    public String test(){
        return "success！";
    }
}
