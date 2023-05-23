package cn.apr.center.service;

import cn.apr.center.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.apr.common.utils.ResponseResult;

public interface ISysUserService extends IService<SysUser> {
    SysUser saveSysUser(SysUser sysUser);

    ResponseResult<SysUser> login(SysUser sysUser);

    ResponseResult logout();
}
