package com.hxy.OnlineDiningRoom.dao;

import com.hxy.OnlineDiningRoom.pojo.Order;
import com.hxy.OnlineDiningRoom.pojo.OrderExample;

import java.util.List;

public interface OrderMapper extends CrudDao<Order>{

    List<Order> selectByExample(OrderExample example);

}