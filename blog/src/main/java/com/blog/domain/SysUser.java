package com.blog.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    public static final long serialVersionUID = 1L;

    @TableId(value = "id",type= IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @TableField("user_name")
    private String username;

    @TableField("user_nickname")
    private String userNickname;

    @TableField("password")
    private String password;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("valid_status")
    @TableLogic
    private Integer validStatus;

    @TableField("update_by")
    private Long updateBy;
}
