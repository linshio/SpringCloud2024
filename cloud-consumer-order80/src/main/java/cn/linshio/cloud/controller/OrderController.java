package cn.linshio.cloud.controller;

import cn.linshio.cloud.entities.PayDTO;
import cn.linshio.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author linshio
 * @create 2024/6/11 17:17
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_SRV_URL = "http://192.168.1.87:8001";

    @Resource
    private RestTemplate restTemplate;

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
