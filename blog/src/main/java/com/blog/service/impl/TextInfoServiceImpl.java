package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.SysMenu;
import com.blog.domain.TextInfo;
import com.blog.mapper.TextInfoMapper;
import com.blog.service.ISysMenuService;
import com.blog.service.ITextInfoService;
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
