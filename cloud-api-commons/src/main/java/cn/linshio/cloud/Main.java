package cn.linshio.cloud;

import java.time.ZonedDateTime;

/**
 * @author linshio
 * @create 2024/6/28 11:31
 */
public class Main {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}
