package cn.linshio.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回类型
 * @author linshio
 * @create 2024/6/11 15:48
 */
@Data
@Accessors(chain = true) //可以支持链式
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    //时间戳
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    //成功
    public static <T> ResultData<T> success(T data){
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }
    //失败
    public static <T> ResultData<T> fail(String code,String message){
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
