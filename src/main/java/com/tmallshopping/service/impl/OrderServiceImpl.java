package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Order;
import com.tmallshopping.entity.OrderExample;
import com.tmallshopping.entity.OrderItem;
import com.tmallshopping.mapper.OrderMapper;
import com.tmallshopping.service.OrderItemService;
import com.tmallshopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 14:09
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    OrderItemService orderItemService;

    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float add(Order order, List<OrderItem> orderItems) {
        float total = 0;
        add(order);

        // 用来模拟当增加订单后出现异常，观察事务管理是否预期发生
        if(false)
            throw new RuntimeException();
        for (OrderItem oi : orderItems) {
            oi.setOrder_id(order.getId());
            orderItemService.update(oi);
            total += oi.getProduct().getPrice() * oi.getNumber();
        }
        return total;
    }

    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public void update(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    public List<Order> list(Integer user_id, String excludedStatus) {
        OrderExample example = new OrderExample();
        example.or().andUser_idEqualTo(user_id).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }


    public List<Order> listAll() {
        OrderExample example = new OrderExample();
        return orderMapper.selectByExample(example);
    }

    @Override
    public List<Order> getAddressAndIphone(Integer user_id) {
        return orderMapper.getAddressAndIphone(user_id);
    }

    @Override
    public Order getByAddress(int order_id) {
        return orderMapper.getByAddress(order_id);
    }

    @Override
    public List<Order> getAll() {
        return orderMapper.getAll();
    }
}
