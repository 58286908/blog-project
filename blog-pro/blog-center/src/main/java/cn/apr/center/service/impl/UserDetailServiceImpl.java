package cn.apr.center.service.impl;

import cn.apr.center.domain.SysUser;
import cn.apr.center.domain.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.apr.center.mapper.SysMenuMapper;
import cn.apr.center.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
