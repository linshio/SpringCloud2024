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
 * @Create 2024/7/9 15:38
 */
@SpringBootApplication
@EnableDiscoveryClient//开启服务注册发现
public class Main9001 {
    public static void main(String[] args) {
        SpringApplication.run(Main9001.class,args);
    }
}