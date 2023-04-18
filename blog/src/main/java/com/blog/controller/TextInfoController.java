package com.blog.controller;

import com.blog.domain.TextInfo;
import com.blog.service.ITextInfoService;
import com.blog.utils.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/blog-textInfo/")
public class TextInfoController {

    @Resource
    private ITextInfoService textInfoService;

    @PostMapping("save")
    @PreAuthorize("hasAuthority('admin')")
    protected TextInfo saveTextInfo(@RequestBody TextInfo textInfo){
        textInfoService.saveTextInfo(textInfo);
        return textInfo;
    }

    @PutMapping()
    public String hello (){
        return "hello";
    }

    @PostMapping("list")
    public ResponseResult list (){
        List<TextInfo> list = textInfoService.list();
        return new ResponseResult(200,list);
    }
}
