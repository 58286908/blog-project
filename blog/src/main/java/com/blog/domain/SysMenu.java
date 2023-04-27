package com.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@TableName("sys_menu")
@Data
public class SysMenu implements Serializable {

    public static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @TableField("menu_name")
    private String menuName;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    @TableField("visible")
    private Integer visable;

    @TableField("status")
    private Integer status;

    @TableField("perms")
    private String perms;

    @TableField("icon")
    private String icon;

    @TableField("parent_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @TableField("valid_status")
    @TableLogic
    private Integer validStatus;

    @TableField(exist = false)
    private List<SysMenu> children;
}
