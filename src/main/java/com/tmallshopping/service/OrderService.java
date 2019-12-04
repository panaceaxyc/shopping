package com.tmallshopping.service;

import com.tmallshopping.entity.Order;
import com.tmallshopping.entity.OrderItem;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 14:07
 */
public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    /**
     * 增加订单，返回一个float类型的数值用来表示该订单的总价
     *
     * @param order
     * @param orderItems
     * @return
     */
    float add(Order order, List<OrderItem> orderItems);

    /**
     * 根据ID返回对应的Order
     *
     * @param id
     * @return
     */
    Order get(int id);

    /**
     * 更新订单
     *
     * @param order
     */
    void update(Order order);

    /**
     * 按照用户、订单状态来查询
     *
     * @param user_id
     * @param excludedStatus
     * @return
     */
    List<Order> list(Integer user_id, String excludedStatus);

    /**
     * 返回所有的订单
     *
     * @return
     */
    List<Order> listAll();

    /**
     * 只查询出 用户的 收货地址 和姓名 和联系方式
     * @param user_id
     * @return
     */
    List<Order> getAddressAndIphone(Integer user_id);

    /**
     * 查询 带收货地址的订单信息
     * @param order_id
     * @return
     */
    Order getByAddress(int order_id);

    /**
     * 查询 带收货地址的订单信息
     * @return
     */
    List<Order> getAll();
}
