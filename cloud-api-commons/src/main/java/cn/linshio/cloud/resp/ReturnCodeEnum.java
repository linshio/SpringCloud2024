package cn.linshio.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

/**
 * 返回的状态码相关信息
 * @author linshio
 * @create 2024/6/11 15:13
 */
@Getter
public enum ReturnCodeEnum {

    //举值-构造-遍历

    //举值
    /**操作失败**/
    RC999("999","操作XXX失败"),
    /**操作成功**/
    RC200("200","success"),
    /**服务降级**/
    RC201("201","服务开启降级保护,请稍后再试!"),
    /**热点参数限流**/
    RC202("202","热点参数限流,请稍后再试!"),
    /**系统规则不满足**/
    RC203("203","系统规则不满足要求,请稍后再试!"),
    /**授权规则不通过**/
    RC204("204","授权规则不通过,请稍后再试!"),
    /**access_denied**/
    RC403("403","无访问权限,请联系管理员授予权限"),
    /**access_denied**/
    RC401("401","匿名用户访问无权限资源时的异常"),
    RC404("404","404页面找不到的异常"),
    /**服务异常**/
    RC500("500","系统异常，请稍后重试"),
    RC375("375","数学运算异常，请稍后重试"),

    INVALID_TOKEN("2001","访问令牌不合法"),
    ACCESS_DENIED("2003","没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED("1001","客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR("1002","用户名或密码错误"),
    BUSINESS_ERROR("1004","业务逻辑异常"),
    UNSUPPORTED_GRANT_TYPE("1003", "不支持的认证模式");

    //构造
    //状态码
    private final String code;
    //返回信息
    private final String message;

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    //遍历
    //传统版本
    public static ReturnCodeEnum getReturnCodeEnum(String code){
        for (ReturnCodeEnum value : ReturnCodeEnum.values()) {
            if (value.getCode().equalsIgnoreCase(code)){
                return value;
            }
        }
        return null;
    }

    //流式版本
    public static ReturnCodeEnum getReturnCodeEnumByStream(String code){
        return Arrays.stream(ReturnCodeEnum.values()).filter(x->x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }
}
