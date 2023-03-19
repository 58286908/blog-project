package com.blog.controller;

import com.blog.domain.TextInfo;
import com.blog.service.ITextInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog/textInfo")
public class TextInfoController {

    @Resource
    private ITextInfoService textInfoService;

    @PostMapping("save")
    protected TextInfo saveTextInfo(){
        TextInfo textInfo = new TextInfo();
        textInfoService.saveTextInfo(textInfo);
        return textInfo;
    }
}
