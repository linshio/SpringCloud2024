package cn.linshio.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author linshio
 * @create 2024/6/11 11:03
 */
//开启服务注册与发现
@EnableDiscoveryClient
@MapperScan("cn.linshio.cloud.mapper")
@SpringBootApplication
public class Main8001 {
    public static void main(String[] args)
    {
        SpringApplication.run(Main8001.class,args);
        //docker run -d -p 8500:8500 --restart=always --name consul-8500 consul:1.6.1 agent -server -bootstrap -ui -node=consul_node_01 -client='0.0.0.0'
        //docker run -id --name=consul -p 8300:8300 -p 8301:8301 -p 8302:8302 -p 8500:8500 -p 8600:8600 -v consul-data:/consul/data consul:latest agent -server -ui -node=n1 -bootstrap-expect=1 -client=0.0.0.0 -advertise=60.204.128.243
    }
}
