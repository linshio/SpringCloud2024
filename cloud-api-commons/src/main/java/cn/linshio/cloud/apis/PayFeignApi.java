package cn.linshio.cloud.apis;

import cn.linshio.cloud.entities.PayDTO;
import cn.linshio.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: PayFeignApi
 * Package: cn.linshio.cloud.apis
 * Description:
 *
 * @Author Linshio
 * @Create 2024/6/19 21:06
 */
//寻找服务的提供者
@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {
    //新增一条流水
    @PostMapping("/pay/add")
    ResultData<String> addPayDTO(@RequestBody PayDTO payDTO);

    //根据id查询一条流水
    @GetMapping("/pay/get/{id}")
    ResultData<PayDTO> getPayInfoMessage(@PathVariable("id") Integer id);

    //测试负载均衡
    @GetMapping("/pay/testConfiguration")
    ResultData<String> testLoadBalanceServes();

    //测试服务熔断与降级 Resilience4j CircuitBreaker 的例子
    @GetMapping("/pay/circuit/{id}")
    ResultData<String> myCircuit(@PathVariable("id") Integer id);

    /**
     * 测试服务隔离 -限制并发数
     * Resilience4j Bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);
}
