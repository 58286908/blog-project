package cn.apr.user.clients;

import cn.apr.user.clients.fallback.BlogClientFallBack;
import cn.apr.user.utils.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "blog-center",value = "blog-center"
        ,fallbackFactory = BlogClientFallBack.class
)
public interface BlogClient {

    @PostMapping("/blog/sys-menu/selectPermsByUserId")
    ResponseResult selectPermsByUserId(@RequestParam("userId") Long userId);
}
