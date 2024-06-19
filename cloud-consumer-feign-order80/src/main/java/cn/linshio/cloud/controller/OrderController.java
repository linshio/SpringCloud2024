package cn.linshio.cloud.controller;

import cn.hutool.core.date.DateUtil;
import cn.linshio.cloud.apis.PayFeignApi;
import cn.linshio.cloud.entities.PayDTO;
import cn.linshio.cloud.resp.ResultData;
import cn.linshio.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author linshio
 * @create 2024/6/11 17:17
 */
@Slf4j
@RestController
@RequestMapping("/consumer/feign")
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    //负载均衡调用测试
    @GetMapping("/pay/testConfiguration")
    public ResultData<String> getLinshioInfo(){
        return payFeignApi.testLoadBalance();
    }

    @PostMapping("/add")
    public ResultData<String> addOrder(@RequestBody PayDTO payDTO){
        log.info("添加订单参数值为==>{}",payDTO);
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/get/{id}")
    public ResultData<PayDTO> getPayInfo(@PathVariable("id") Integer id){
        log.info("查询单个订单参数值为==>{}",id);
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try
        {
            System.out.println("调用开始-----:"+ DateUtil.now());
            resultData = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束-----:"+DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return resultData;
    }
//
//    @GetMapping("/update")
//    public void updatePay(PayDTO payDTO){
//        log.info("修改订单参数值为==>{}",payDTO);
//         restTemplate.put(PAYMENT_SRV_URL+"/pay/update",payDTO);
//    }
//
//    @GetMapping("/del/{id}")
//    public void deletePay(@PathVariable("id") Integer id){
//        log.info("删除订单参数值为==>{}",id);
//        restTemplate.delete(PAYMENT_SRV_URL+"/pay/del/"+id,id);
//    }
}
