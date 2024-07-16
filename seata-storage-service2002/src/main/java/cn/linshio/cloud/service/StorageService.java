package cn.linshio.cloud.service;

/**
 * ClassName: StorageService
 * Package: cn.linshio.cloud.service
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/16 10:43
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
