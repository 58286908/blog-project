package cn.apr.common.feign.fallback;

import cn.apr.common.feign.UserClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import cn.apr.common.utils.ResponseResult;

@Component
@Slf4j
public class UserClientFallBack implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        throwable.printStackTrace();
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new UserClient() {
            @Override
            public ResponseResult getUserByName(String userName) {
                return ResponseResult.error("getUserByName调用失败");
            }
        };
    }
}
