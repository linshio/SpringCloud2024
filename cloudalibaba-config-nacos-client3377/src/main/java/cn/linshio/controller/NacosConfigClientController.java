package cn.linshio.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: NacosConfigClientController
 * Package: cn.linshio.controller
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/10 09:21
 */
@RestController
@RefreshScope//在控制器中@RefreshScope注解使当前类的配置支持nacos的动态刷新功能
public class NacosConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
