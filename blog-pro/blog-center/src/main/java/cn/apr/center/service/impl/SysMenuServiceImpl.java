package cn.apr.center.service.impl;

import cn.apr.center.domain.SysMenu;
import cn.apr.center.mapper.SysMenuMapper;
import cn.apr.center.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
}
