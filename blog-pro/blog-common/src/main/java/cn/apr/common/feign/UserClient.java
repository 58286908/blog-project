package cn.apr.common.feign;

import cn.apr.common.feign.fallback.UserClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.apr.common.utils.ResponseResult;

@Component
@FeignClient(value = "blog-user",fallbackFactory = UserClientFallBack.class)
public interface UserClient {

    @PostMapping("/sys-user/getUserByName")
    ResponseResult getUserByName(@RequestParam("userName")String userName);
}
