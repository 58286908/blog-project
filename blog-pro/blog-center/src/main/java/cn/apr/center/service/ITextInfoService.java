package cn.apr.center.service;

import cn.apr.center.domain.TextInfo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ITextInfoService extends IService<TextInfo> {

    TextInfo saveTextInfo(TextInfo textInfo);
}
