package cn.linshio.cloud.route;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.AfterRoutePredicateFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * ClassName: MyGatewayFilterFactory
 * Package: cn.linshio.cloud.route
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/8 21:26
 */
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {

    public MyGatewayFilterFactory(){
        super(MyGatewayFilterFactory.Config.class);
    }

    @Override
    public GatewayFilter apply(MyGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //首先获取到request对象
                ServerHttpRequest request = exchange.getRequest();
                System.out.println("进入了自定义的网关过滤器：MyGatewayFilterFactory "+config.status);
                //如果请求中包含了指定的key 则进行放行
                if (request.getQueryParams().containsKey("linshio")){
                    return chain.filter(exchange);
                }else {
                    //设置非法的响应
                    exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                    return exchange.getResponse().setComplete();
                }
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("status");
    }

    public static class Config {
        @Setter @Getter
        private String status;//设定一个状态值标志位，它等于多少，匹配和才可以进行访问
    }
}
