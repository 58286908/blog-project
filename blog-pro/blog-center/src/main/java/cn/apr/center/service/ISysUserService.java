package cn.apr.center.service;

import cn.apr.center.domain.SysUser;
import cn.apr.center.utils.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ISysUserService extends IService<SysUser> {
    SysUser saveSysUser(SysUser sysUser);

    ResponseResult<SysUser> login(SysUser sysUser);

    ResponseResult logout();
}
