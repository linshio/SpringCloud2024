package cn.linshio.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author linshio
 * @create 2024/6/11 11:03
 */
@MapperScan("cn.linshio.cloud.mapper")
@SpringBootApplication
public class Main8001 {
    public static void main(String[] args)
    {
        SpringApplication.run(Main8001.class,args);
    }
}
