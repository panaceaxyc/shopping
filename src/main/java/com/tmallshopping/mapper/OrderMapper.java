package com.tmallshopping.mapper;

import com.tmallshopping.entity.Order;
import com.tmallshopping.entity.OrderExample;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getAddressAndIphone(Integer user_id);

    Order getByAddress(int order_id);

    List<Order> getAll();
}