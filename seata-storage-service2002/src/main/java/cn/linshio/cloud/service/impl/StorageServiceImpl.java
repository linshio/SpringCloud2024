package cn.linshio.cloud.service.impl;

import cn.linshio.cloud.mapper.StorageMapper;
import cn.linshio.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: StorageServiceImpl
 * Package: cn.linshio.cloud.service.impl
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/16 10:44
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageMapper.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
}
