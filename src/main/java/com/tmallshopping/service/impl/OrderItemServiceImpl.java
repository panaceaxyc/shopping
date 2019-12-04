package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Order;
import com.tmallshopping.entity.OrderItem;
import com.tmallshopping.entity.OrderItemExample;
import com.tmallshopping.entity.Product;
import com.tmallshopping.mapper.OrderItemMapper;
import com.tmallshopping.service.OrderItemService;
import com.tmallshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 12:31
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductService productService;

    public List<OrderItem> listByUserId(Integer user_id) {
        OrderItemExample example = new OrderItemExample();
        example.or().andUser_idEqualTo(user_id);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        setProduct(orderItems);
        return orderItems;
    }

    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKey(orderItem);
    }

    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }


    public List<OrderItem> listForCart(Integer user_id) {
        OrderItemExample example = new OrderItemExample();
        //Product product = new Product();
        example.or().andUser_idEqualTo(user_id).andOrder_idIsNull();
        List<OrderItem> result = orderItemMapper.selectByExample(example);
        setProduct(result);
        return result;
    }

    @Override
    public void delete(Integer id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderItem getById(int id) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
        setProduct(orderItem);
        return orderItem;
    }

    public void fill(List<Order> orders) {
        for (Order o : orders) {
            fill(o);
        }
    }

    public void fill(Order order) {
        OrderItemExample example = new OrderItemExample();
        example.or().andOrder_idEqualTo(order.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        setProduct(orderItems);

        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : orderItems) {
            total += oi.getNumber() * oi.getProduct().getPrice();
            totalNumber += oi.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }

    @Override
    public List<OrderItem> selCartList(Integer user_id) {
        return orderItemMapper.selCartList(user_id);
    }

    public void setProduct(List<OrderItem> ois) {
        for (OrderItem oi : ois) {
            setProduct(oi);
        }
    }

    private void setProduct(OrderItem oi) {
        Product p = productService.get(oi.getProduct_id());
        oi.setProduct(p);
    }
}
