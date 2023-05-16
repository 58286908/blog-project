package cn.apr.center.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    private Integer code;

    private String msg;

    private  T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }


    public static ResponseResult success(String msg) {
        return success(msg, (Object)null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult(200, msg, data);
    }



    public static ResponseResult error() {
        return error("操作失败");
    }

    public static ResponseResult error(String msg) {
        return error(msg, (Object)null);
    }

    public static <T> ResponseResult<T> error(String msg, T data) {
        return new ResponseResult(500, msg, data);
    }
}
