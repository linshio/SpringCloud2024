package cn.linshio.cloud.service;

import cn.linshio.cloud.entities.Pay;

import java.util.List;

/**
 * @author linshio
 * @create 2024/6/11 11:39
 */
public interface PayService {
    int add(Pay pay);
    int delete(Integer id);
    int update(Pay pay);
    Pay getById(Integer id);
    List<Pay> getAll();
}
