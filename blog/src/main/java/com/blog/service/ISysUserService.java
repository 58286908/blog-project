package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.SysUser;
import com.blog.utils.ResponseResult;

public interface ISysUserService extends IService<SysUser> {
    SysUser saveSysUser(SysUser sysUser);

    ResponseResult<SysUser> login(SysUser sysUser);

    ResponseResult logout();
}
