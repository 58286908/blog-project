package cn.apr.center.service.impl;

import cn.apr.center.domain.TextInfo;
import cn.apr.center.service.ISysMenuService;
import cn.apr.center.service.ITextInfoService;
import cn.apr.center.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.apr.center.mapper.TextInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TextInfoServiceImpl extends ServiceImpl<TextInfoMapper, TextInfo> implements ITextInfoService {

    @Resource
    private ISysMenuService sysMenuService;

    @Override
    public TextInfo saveTextInfo(TextInfo textInfo) {
        SysMenu sysMenu = sysMenuService.getById(textInfo.getMenuId());
        if (sysMenu != null) {
            textInfo.setMenuName(sysMenu.getMenuName());
        }
        save(textInfo);
        return textInfo;
    }
}
