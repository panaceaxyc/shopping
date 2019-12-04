package com.tmallshopping.mapper;

import com.tmallshopping.entity.Address;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/19 20:25
 */
public interface AddressMapper {

    /**根据用户ID来查询收货地址*/
    public List<Address> getAllAddressByUserId(Integer userId);

    /**新增收货地址*/
    public int insertSelective(Address address);

    /**删除收货地址*/
    public int deleteByPrimaryKey(Integer id);

    /**修改 也就是保存收货地址*/
    public int updateByPrimaryKeySelective(Address address);

    /**根据订单信息的地址ID 查询收货地址*/
    Address selectByPrimaryKey(Integer id);
}
