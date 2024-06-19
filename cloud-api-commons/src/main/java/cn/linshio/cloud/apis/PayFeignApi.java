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
    ResultData<String> addPay(@RequestBody PayDTO payDTO);

    //根据id查询一条流水
    @GetMapping("/pay/get/{id}")
    ResultData<PayDTO> getPayInfo(@PathVariable("id") Integer id);

    //测试负载均衡
    @GetMapping("/pay/testConfiguration")
    ResultData<String> testLoadBalance();
}
