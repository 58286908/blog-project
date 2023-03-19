package com.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("text_info")
public class TextInfo {

    @TableId(value = "id",type= IdType.ASSIGN_ID)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;
}
