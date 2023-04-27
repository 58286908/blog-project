package com.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("text_info")
public class TextInfo implements Serializable {

    public static final long serialVersionUID = 1L;

    @TableId(value = "id",type= IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("menu_name")
    private String menuName;

    @TableField("menu_id")
    private Long menuId;

    @TableField("valid_status")
    @TableLogic
    private Integer validStatus;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
