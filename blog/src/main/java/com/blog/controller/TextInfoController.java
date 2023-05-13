package com.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.domain.SysMenu;
import com.blog.domain.TextInfo;
import com.blog.service.ISysMenuService;
import com.blog.service.ITextInfoService;
import com.blog.utils.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blog-textInfo/")
public class TextInfoController {

    @Resource
    private ITextInfoService textInfoService;
    @Resource
    private ISysMenuService sysMenuService;
    private int testNum = 0;

    @PostMapping("save")
    @PreAuthorize("hasAuthority('admin')")
    protected ResponseResult saveTextInfo(@RequestBody TextInfo textInfo) {
        textInfoService.saveTextInfo(textInfo);
        return new ResponseResult(200, "保存成功");
    }

    @PutMapping("update")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult update(@RequestBody @Valid TextInfo textInfo){
        textInfoService.updateById(textInfo);
        return new ResponseResult(200,"修改成功");
    }

    @GetMapping("id/{id}")
    public ResponseResult getById(@PathVariable String id) {
        TextInfo textInfo = textInfoService.getById(id);
        return new ResponseResult(200, textInfo);
    }

    @PostMapping("list")
    public ResponseResult list(@RequestBody String menuName) {
        testNum+=1;
        List<TextInfo> list;
        QueryWrapper<TextInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(TextInfo::getCreateTime);
        if (StringUtils.hasText(menuName) && !menuName.equals("无")) {
            queryWrapper.lambda().eq(TextInfo::getMenuName, menuName);
            list = textInfoService.list(queryWrapper);
        } else {
            list = textInfoService.list(queryWrapper);
        }
        List<SysMenu> menuList = sysMenuService.list();
        for (TextInfo textInfo : list) {
            for (SysMenu sysMenu : menuList) {
                if (textInfo.getMenuId().longValue() == sysMenu.getId().longValue())
                    textInfo.setMenuName(sysMenu.getMenuName());
            }
        }
        return new ResponseResult(200, list);
    }


}
