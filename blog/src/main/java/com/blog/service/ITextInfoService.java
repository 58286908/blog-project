package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.TextInfo;

public interface ITextInfoService extends IService<TextInfo> {

    TextInfo saveTextInfo(TextInfo textInfo);
}
