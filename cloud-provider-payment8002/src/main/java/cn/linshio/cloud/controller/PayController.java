package cn.linshio.cloud.controller;


import cn.linshio.cloud.entities.Pay;
import cn.linshio.cloud.entities.PayDTO;
import cn.linshio.cloud.resp.ResultData;
import cn.linshio.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linshio
 * @create 2024/6/11 11:53
 */
@Slf4j
@Tag(name = "支付微服务模块",description = "支付的CRUD")
@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private PayService payService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/testConfiguration")
    public ResultData<String> testConfiguration(@Value("${linshio.info}") String info){
        return ResultData.success("the server port is :"+port+" the info is :"+info);
    }


    @PostMapping("/add")
    @Operation(summary = "新增",description = "新增支付的流水方法，json字符串作为参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        log.info("添加订单参数值为==>{}",pay);
        int add = payService.add(pay);
        return ResultData.success("成功插入记录，返回值为："+add);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<String> deletePay(@PathVariable("id") Integer id){
        log.info("删除订单参数值为==>{}",id);
        int delete = payService.delete(id);
        return ResultData.success("成功删除记录，返回值为："+delete);
    }

    @PutMapping("/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        log.info("修改订单参数值为==>{}",payDTO);
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int update = payService.update(pay);
        return ResultData.success("成功修改记录，返回值为："+update);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        log.info("查询单个订单参数值为==>{}",id);
        if (id == -4) {
            throw new RuntimeException("id不能为负数");
        }
        return ResultData.success(payService.getById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "查询所有的流水",description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAll(){
        List<Pay> all = payService.getAll();
        return ResultData.success(all);
    }

}
