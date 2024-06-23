package cn.linshio.cloud.controller;

import cn.linshio.cloud.apis.PayFeignApi;
import cn.linshio.cloud.resp.ResultData;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: OrderCircuitController
 * Package: cn.linshio.cloud.controller
 * Description:  Resilience4j CircuitBreaker 的例子
 *
 * @Author Linshio
 * @Create 2024/6/23 09:45
 */
@RestController
public class OrderCircuitController
{
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/circuit/{id}")
    //断路器注解 需要开启在哪个服务之上 并且兜底的方法是哪个
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public ResultData<String> myCircuitBreaker(@PathVariable("id") Integer id)
    {
        return payFeignApi.myCircuit(id);
    }

    //myCircuitFallback就是服务降级后的兜底处理方法
    public ResultData<String> myCircuitFallback(Integer id,Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return ResultData.success("myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }
}