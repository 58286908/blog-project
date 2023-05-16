package cn.apr.user.service;

import cn.apr.user.utils.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.apr.user.domain.SysUser;

public interface ISysUserService extends IService<SysUser> {
    SysUser saveSysUser(SysUser sysUser);

    ResponseResult<SysUser> login(SysUser sysUser);

    ResponseResult logout();
}
