package cn.linshio.cloud.service;

import org.apache.ibatis.annotations.Param;

/**
 * ClassName: AccountService
 * Package: cn.linshio.cloud.service
 * Description:
 *
 * @Author Linshio
 * @Create 2024/7/16 10:51
 */
public interface AccountService {
    /**
            * 扣减账户余额
     * @param userId 用户id
     * @param money 本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
