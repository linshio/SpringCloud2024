package cn.linshio.cloud.apis;

import cn.linshio.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: AccountFeignApi
 * Package: cn.linshio.cloud.apis
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/15 20:18
 */
@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金钱数量
     * @return ResultData
     */
    @PostMapping("/account/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
