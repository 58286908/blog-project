package com.blog.controller;

import com.blog.domain.SysMenu;
import com.blog.service.ISysMenuService;
import com.blog.utils.ResponseResult;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/sys-menu/")
public class SysMenuController {

    @Resource
    private ISysMenuService sysMenuService;

    @PostMapping("save")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return new ResponseResult(200, "保存成功");
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult update(@RequestBody SysMenu sysMenu){
        sysMenuService.updateById(sysMenu);
        return new ResponseResult(200,"修改成功");
    }

    @GetMapping("listByMenu")
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseResult listByMenu() {
        List<SysMenu> sysMenuList = sysMenuService.list();
        List<SysMenu> deleteList = new ArrayList<>();
        if (sysMenuList != null && sysMenuList.size() > 0) {
            for (int i = 0; i < sysMenuList.size(); i++) {
                SysMenu sysMenu = sysMenuList.get(i);
                List<SysMenu> childList = new ArrayList<>();
                if (sysMenu.getParentId() == null) {
                    for (SysMenu menuItem : sysMenuList) {
                        if (menuItem.getParentId() != null)
                            if (sysMenu.getId().longValue() == menuItem.getParentId().longValue()){
                                childList.add(menuItem);
                            }
                            else
                                continue;
                    }
                    sysMenu.setChildren(childList);

                }else{
                    deleteList.add(sysMenu);
                }
            }
            //将处理好的数据中的子菜单从中删除
            Iterator<SysMenu> iterable = sysMenuList.iterator();
            while (iterable.hasNext()){
                SysMenu sysMenu = iterable.next();
                for(SysMenu d : deleteList){
                    if(sysMenu.getId().toString().equals(d.getId().toString())){
                        iterable.remove();
                    }
                }




            }
        }
        return new ResponseResult(200, sysMenuList);
    }
}
