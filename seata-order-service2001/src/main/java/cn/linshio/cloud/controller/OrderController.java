package cn.linshio.cloud.controller;

import cn.linshio.cloud.entities.Order;
import cn.linshio.cloud.resp.ResultData;
import cn.linshio.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: OrderController
 * Package: cn.linshio.cloud.controller
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/16 09:56
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public ResultData<Order> create(Order order)
    {
        orderService.create(order);
        return ResultData.success(order);
    }
}