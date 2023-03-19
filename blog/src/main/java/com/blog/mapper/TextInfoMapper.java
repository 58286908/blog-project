package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.TextInfo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface TextInfoMapper extends BaseMapper<TextInfo> {

}
