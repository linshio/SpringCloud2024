package cn.linshio.cloud.apis;

import cn.linshio.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: PayFeignSentinelApi
 * Package: cn.linshio.cloud.apis
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/14 16:42
 */
@FeignClient(value = "nacos-payment-provider",fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {
    @GetMapping("/pay/nacos/get/{orderNo}")
    ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
