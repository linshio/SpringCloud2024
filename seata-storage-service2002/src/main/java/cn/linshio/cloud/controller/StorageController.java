package cn.linshio.cloud.controller;

import cn.linshio.cloud.resp.ResultData;
import cn.linshio.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: StorageController
 * Package: cn.linshio.cloud.controller
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/16 10:45
 */
@RestController
public class StorageController
{
    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public ResultData<String> decrease(Long productId, Integer count) {

        storageService.decrease(productId, count);
        return ResultData.success("扣减库存成功!");
    }
}
