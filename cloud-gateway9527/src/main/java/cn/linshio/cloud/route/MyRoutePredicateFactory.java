package cn.linshio.cloud.route;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * ClassName: MyRoutePredicateFactory
 * Package: cn.linshio.cloud.route
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/8 10:12
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    //空参构造
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    //重写Apply方法
    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config)
    {

        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //检查request的参数里面的userType是否为指定的值，符合通配就通过
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                //如果参数不存在就直接不通过
                if (userType==null) return false;
                //如果参数相同就进行比较
                if (userType.equals(config.getUserType())){
                    return true;
                }
                return false;
            }
        };
    }

    //编写Apply方法所需的静态内部类 这个userType是从配置类中读取到的
    @Validated
    public static class Config{
        @Setter
        @Getter
        @NotEmpty
        private String userType; //钻、金、银等用户等级
    }

    //重写shortcutFieldOrder 使得配置文件支持短格式
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }
}
