package com.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("sys_menu")
@Data
public class SysMenu implements Serializable {

    public static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
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
}
