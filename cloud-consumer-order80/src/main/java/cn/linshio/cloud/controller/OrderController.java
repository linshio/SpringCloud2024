package cn.linshio.cloud.controller;

import cn.linshio.cloud.entities.PayDTO;
import cn.linshio.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author linshio
 * @create 2024/6/11 17:17
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

//    public static final String PAYMENT_SRV_URL = "http://192.168.1.87:8001";
    //服务注册中心上的微服务名称
    public static final String PAYMENT_SRV_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;


    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

    @GetMapping("/pay/testConfiguration")
    public ResultData getLinshioInfo(){
       return restTemplate.getForObject(PAYMENT_SRV_URL+"/pay/testConfiguration",ResultData.class);
    }

    @GetMapping("/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        log.info("添加订单参数值为==>{}",payDTO);
        return restTemplate.postForObject(PAYMENT_SRV_URL+"/pay/add",payDTO,ResultData.class);
    }

    @GetMapping("/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        log.info("查询单个订单参数值为==>{}",id);
        return restTemplate.getForObject(PAYMENT_SRV_URL+"/pay/get/"+id,ResultData.class,id);
    }

    @GetMapping("/update")
    public void updatePay(PayDTO payDTO){
        log.info("修改订单参数值为==>{}",payDTO);
         restTemplate.put(PAYMENT_SRV_URL+"/pay/update",payDTO);
    }

    @GetMapping("/del/{id}")
    public void deletePay(@PathVariable("id") Integer id){
        log.info("删除订单参数值为==>{}",id);
        restTemplate.delete(PAYMENT_SRV_URL+"/pay/del/"+id,id);
    }
}
