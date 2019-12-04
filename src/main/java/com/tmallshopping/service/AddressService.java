package com.tmallshopping.service;

import com.tmallshopping.entity.Address;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/13 15:43
 */
public interface AddressService {

    /**根据用户ID来查询收货地址*/
    List<Address> getAllAddressByUserId(Integer userId);

    /**新增收货地址*/
    int insertSelective(Address address);

    /**删除地址*/
    int deleteByPrimaryKey(Integer id);

    /**修改 也就是保存收货地址*/
    int updateByPrimaryKeySelective(Address address);

    /**根据订单信息的地址ID 查询收货地址*/
    Address selectByPrimaryKey(Integer id);
}
