package cn.linshio.cloud.service;

import cn.linshio.cloud.entities.Order;

/**
 * ClassName: OrderService
 * Package: cn.linshio.cloud.service
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/15 21:06
 */
public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}
