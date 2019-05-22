package com.demo.cloud.user.service.impl;

import com.demo.cloud.user.config.RedisRepository;
import com.demo.cloud.user.dao.QopOrdersDao;
import com.demo.cloud.user.model.QopOrders;
import com.demo.cloud.user.service.IDataSynchronizeService;
import com.demo.cloud.user.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DataSynchronizeServiceImpl implements IDataSynchronizeService {

    final RedisRepository redisRepository;

    final QopOrdersDao qopOrdersDao;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public DataSynchronizeServiceImpl(QopOrdersDao qopOrdersDao, RedisRepository redisRepository) {
        this.qopOrdersDao = qopOrdersDao;
        this.redisRepository = redisRepository;
    }


    @Override
    public List<QopOrders> queryQopOrdersByBuyerName(String buyerName) {

        Example example = new Example(QopOrders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buyerName", buyerName);
        return qopOrdersDao.selectByExample(example);

    }

    @Override
    public boolean synchronizeDBData2Redis(int db, List<QopOrders> list) {
        redisRepository.switchDB(db);
        try {
            list.forEach(this::accept);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private void accept(QopOrders rs) {
        String red_key = MessageFormat.format("api.order.master.{0}.{1}", rs.getBuyerName(), rs.getOrderCode());
        Map<String, Object> stringObjectMap = JSONUtils.parse(JSONUtils.toJson(rs), Map.class);
        assert stringObjectMap != null;
        stringObjectMap.forEach((key, val) -> redisRepository.setHash(red_key,
                String.valueOf(key), String.valueOf(val)));
    }
}
