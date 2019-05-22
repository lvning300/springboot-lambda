package com.demo.cloud.user.controller;

import com.demo.cloud.user.service.IDataSynchronizeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/sync")
public class DataSynchronizeServiceController {


    @Autowired
    private IDataSynchronizeService dataSynchronizeService;


    @ApiOperation(value = "根据BUYER_NAME同步订单数据", response = Boolean.class)
    @RequestMapping(path = "/to-redis/{buyerName}/{redisDB}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean toRedis(@PathVariable(value = "buyerName", name = "buyerName")
                                   String buyerName,
                           @PathVariable(value = "redisDB", name = "redisDB")
                                   Integer redisDB) {


        return dataSynchronizeService.synchronizeDBData2Redis(redisDB, dataSynchronizeService.queryQopOrdersByBuyerName(buyerName));
    }
}
