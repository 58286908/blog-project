package cn.apr.user.service.impl;

import cn.apr.user.service.ISysUserService;
import cn.apr.user.utils.ResponseResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.apr.user.domain.LoginUser;
import cn.apr.user.domain.SysUser;
import cn.apr.user.mapper.SysUserMapper;
import cn.apr.user.utils.JwtUtil;
import cn.apr.user.utils.RedisCaChe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCaChe redisCaChe;

    @Override
    public SysUser saveSysUser(SysUser sysUser) {
        BCryptPasswordEncoder bcPassWord = new BCryptPasswordEncoder();
        sysUser.setPassword(bcPassWord.encode(sysUser.getPassword()));
        save(sysUser);
        return sysUser;
    }

    @Override
    public ResponseResult<SysUser> login(SysUser sysUser) {
        //存储用户信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(),sysUser.getPassword());
        //验证用户
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            throw new RuntimeException("认证未通过");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getSysUser().getId().toString();
        String nickName = loginUser.getSysUser().getUserNickname();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap<>();
        map.put("Authorization",jwt);
        map.put("nickName",nickName);
        redisCaChe.setCacheObject("login:"+userId,loginUser);

        //如果认证通过了，使用userId生成一个jwt，jwt存入返回
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(200);
        responseResult.setMsg("操作成功");
        responseResult.setData(map);
        return responseResult;
    }

    @Override
    public ResponseResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId= loginUser.getSysUser().getId();
        redisCaChe.deleteObject("login:"+userId);
        return new ResponseResult(200,"删除成功");
    }
}
