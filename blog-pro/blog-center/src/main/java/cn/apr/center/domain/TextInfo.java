package cn.apr.center.domain;

import com.baomidou.mybatisplus.annotation.*;
import cn.apr.center.annotation.BlogLogs;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @BlogLogs(output = false)
    @TableField("title")
    private String title;

    @BlogLogs(output = false)
    @TableField("content")
    private String content;

    @BlogLogs(output = false)
    @TableField("menu_name")
    private String menuName;

    @TableField("menu_id")
    private Long menuId;

    @TableField("valid_status")
    @TableLogic
    private Integer validStatus;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @TableField("text_content")
    private String textContent;
}
