package com.demo.cloud.user.dao;

import com.demo.cloud.user.model.QopOrders;
import com.demo.cloud.user.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface QopOrdersDao extends Mapper<QopOrders>, MySqlMapper<QopOrders> {


}
