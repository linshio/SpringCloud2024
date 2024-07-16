package cn.linshio.cloud.apis;

import cn.linshio.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: StorageFeignApi
 * Package: cn.linshio.cloud.apis
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/15 20:17
 */
@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 库存数量
     * @return ResultData
     */
    @PostMapping(value = "/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
