package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.domain.LoginUser;
import com.blog.domain.SysUser;
import com.blog.mapper.SysMenuMapper;
import com.blog.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,userName);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        if(Objects.isNull(sysUser)){
            throw new RuntimeException("用户名或密码错误");
        }

        List<String>permsList = sysMenuMapper.selectPermsByUserId(sysUser.getId());

//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        return new LoginUser(sysUser,permsList);
    }
}
