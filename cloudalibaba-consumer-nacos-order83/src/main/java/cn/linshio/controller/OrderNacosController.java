package cn.linshio.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: OrderNacosController
 * Package: cn.linshio.controller
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/9 16:25
 */
@RestController
public class OrderNacosController
{
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")//请求的服务名
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id)
    {
        String result = restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class);
        return result+"\t"+"    我是OrderNacosController83调用者。。。。。。";
    }
}