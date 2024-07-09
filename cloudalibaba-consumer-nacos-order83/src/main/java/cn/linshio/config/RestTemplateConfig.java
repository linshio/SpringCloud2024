package cn.linshio.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: RestTemplateConfig
 * Package: cn.linshio.config
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/9 16:24
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced//负载均衡注解
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
