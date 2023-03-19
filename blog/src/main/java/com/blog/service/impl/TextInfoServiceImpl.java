package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.TextInfo;
import com.blog.mapper.TextInfoMapper;
import com.blog.service.ITextInfoService;
import org.springframework.stereotype.Service;

@Service
public class TextInfoServiceImpl extends ServiceImpl<TextInfoMapper, TextInfo> implements ITextInfoService {
    @Override
    public TextInfo saveTextInfo(TextInfo textInfo) {
        save(textInfo);
        return textInfo;
    }
}
