package cn.linshio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: ${NAME}
 * Package: cn.linshio
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/9 16:22
 */
@SpringBootApplication
@EnableDiscoveryClient//开启服务注册与发现
public class Main83 {
    public static void main(String[] args) {
        SpringApplication.run(Main83.class,args);
    }
}