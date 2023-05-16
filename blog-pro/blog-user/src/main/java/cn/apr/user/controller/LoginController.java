package cn.apr.user.controller;

import cn.apr.user.utils.ResponseResult;
import cn.apr.user.domain.SysUser;
import cn.apr.user.service.ISysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys-user/")
public class LoginController {

    @Resource
    private ISysUserService sysUserService;
//    @Resource
//    private SysMenuMapper sysMenuMapper;

    @PostMapping("login")
    public ResponseResult<SysUser> login(@RequestBody SysUser sysUser){

        return sysUserService.login(sysUser);
    }

    @RequestMapping("logout")
    public ResponseResult logout (){
        return sysUserService.logout();
    }

//    @RequestMapping("hello")
//    @PreAuthorize("hasAuthority('admin')")
//    public String hello(){
//        List<String> list = sysMenuMapper.selectPermsByUserId(1L);
//        return list.get(0);
//    }

    @RequestMapping("test")
    public String test(){
        return "success！";
    }
}
