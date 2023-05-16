package cn.apr.user.clients.fallback;

import cn.apr.user.clients.BlogClient;
import cn.apr.user.utils.ResponseResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlogClientFallBack implements FallbackFactory<BlogClient> {


    @Override
    public BlogClient create(Throwable throwable) {
        throwable.printStackTrace();
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new BlogClient() {
            @Override
            public ResponseResult selectPermsByUserId(Long userId) {
                return ResponseResult.error("selectPermsByUserId服务调用失败");
            }
        };
    }
}
