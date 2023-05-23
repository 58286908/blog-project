package cn.apr.common.feign;

import cn.apr.common.feign.fallback.BlogClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.apr.common.utils.ResponseResult;

@Component
@FeignClient(contextId = "blog-center",value = "blog-center",fallbackFactory = BlogClientFallBack.class)
public interface BlogClient {

    @PostMapping("/sys-menu/selectPermsByUserId")
    ResponseResult selectPermsByUserId(@RequestParam("userId") Long userId);
}
