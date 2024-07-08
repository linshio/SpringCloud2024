package cn.linshio.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: MyGlobalFilter
 * Package: cn.linshio.cloud.filter
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/8 15:18
 */
@Slf4j
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    //开始访问时间
    private static final String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //先记录下访问接口的开始时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME,System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            //获取接口的开始时间
            Long beginVisitTime = exchange.getAttribute(BEGIN_VISIT_TIME);
            //如果时间存在打印相关信息
            if (beginVisitTime!=null){
                log.info("访问接口主机："+exchange.getRequest().getURI().getHost());
                log.info("访问接口端口："+exchange.getRequest().getURI().getPort());
                log.info("访问接口URL："+exchange.getRequest().getURI().getPath());
                log.info("访问接口URL参数："+exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口时长："+(System.currentTimeMillis()-beginVisitTime)+"ms");
                log.info("我是美丽分割线: ###################################################");
                System.out.println();
            }
        }));
    }

    //数字越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
