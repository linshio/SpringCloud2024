package cn.linshio.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: EmpowerController
 * Package: cn.linshio.controller
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/14 14:45
 */
@RestController
@Slf4j
public class EmpowerController //Empower授权规则，用来处理请求的来源
{
    @GetMapping(value = "/empower")
    @SentinelResource(value = "empower",blockHandler = "handlerEmpower")
    public String requestSentinel4(){
        log.info("测试Sentinel授权规则empower");
        return "Sentinel授权规则";
    }

    public String handlerEmpower(BlockException e)
    {
        return "触发了熔断保护";
    }
}
