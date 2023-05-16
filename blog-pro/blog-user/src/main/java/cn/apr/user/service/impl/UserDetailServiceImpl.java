package cn.apr.user.service.impl;

import cn.apr.user.clients.BlogClient;
import cn.apr.user.utils.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.apr.user.domain.LoginUser;
import cn.apr.user.domain.SysUser;
import cn.apr.user.mapper.SysUserMapper;
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
    private BlogClient blogClient;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername, userName);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        if (Objects.isNull(sysUser)) {
            throw new RuntimeException("用户名或密码错误");
        }

        ResponseResult result = blogClient.selectPermsByUserId(sysUser.getId());
        List<String> permsList = (List<String>) result.getData();
//        List<String> permsList = new ArrayList<>(Arrays.asList("test","admin"));
        return new LoginUser(sysUser, permsList);
    }
}
