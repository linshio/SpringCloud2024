package cn.linshio.cloud.apis;

import cn.linshio.cloud.resp.ResultData;
import cn.linshio.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * ClassName: PayFeignSentinelApiFallBack
 * Package: cn.linshio.cloud.apis
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/14 16:43
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
