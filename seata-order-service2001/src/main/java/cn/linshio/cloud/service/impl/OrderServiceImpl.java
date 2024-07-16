package cn.linshio.cloud.service.impl;

import cn.linshio.cloud.apis.AccountFeignApi;
import cn.linshio.cloud.apis.StorageFeignApi;
import cn.linshio.cloud.entities.Order;
import cn.linshio.cloud.mapper.OrderMapper;
import cn.linshio.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * ClassName: OrderServiceImpl
 * Package: cn.linshio.cloud.service.impl
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/15 21:07
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource//订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;
    @Resource//订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;
    @Override
    public void create(Order order) {
        //xid检查
        String xid = RootContext.getXID();
        //1. 新建订单
        log.info("==================>开始新建订单"+"\t"+"xid_order:" +xid);
        //设置订单的状态为0 ，开始创建订单
        order.setStatus(0);
        int result = orderMapper.insert(order);
        //插入订单后获得插入的sql中的对象
        Order orderFromDB = null;
        if (result>0){
            orderFromDB = orderMapper.selectOne(order);
            log.info("-------> 新建订单成功，orderFromDB info: "+orderFromDB);
            System.out.println();
            //扣减库存
            //2. 扣减库存
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(),orderFromDB.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();
            //3. 扣减账号余额
            //扣减账户余额
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(),orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            System.out.println();
            //修改订单的状态
            //订单状态status：0：创建中；1：已完结
            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);
            // 以下为修改查询的条件
            Example whereCondition=new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId",orderFromDB.getUserId());
            criteria.andEqualTo("status",0);
            //修改数据库中订单的状态
            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);
            log.info("-------> 修改订单状态完成"+"\t"+updateResult);
            log.info("-------> orderFromDB info: "+orderFromDB);
        }
        System.out.println();
        log.info("==================>结束新建订单"+"\t"+"xid_order:" +xid);
    }
}
