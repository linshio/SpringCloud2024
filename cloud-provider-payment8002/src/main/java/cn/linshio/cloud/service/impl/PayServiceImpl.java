package cn.linshio.cloud.service.impl;

import cn.linshio.cloud.entities.Pay;
import cn.linshio.cloud.mapper.PayMapper;
import cn.linshio.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linshio
 * @create 2024/6/11 11:44
 */
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
