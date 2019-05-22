package com.demo.cloud.user.service;

import com.demo.cloud.user.model.QopOrders;

import java.util.List;

public interface IDataSynchronizeService {


    List<QopOrders> queryQopOrdersByBuyerName(String buyerName);


    boolean synchronizeDBData2Redis(int db, List<QopOrders> list);
}
