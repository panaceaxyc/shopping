package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Address;
import com.tmallshopping.mapper.AddressMapper;
import com.tmallshopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/19 20:24
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**根据用户ID来查询收货地址*/
    public List<Address> getAllAddressByUserId(Integer userId) {
        return addressMapper.getAllAddressByUserId(userId);
    }

    /**新增收货地址*/
    public int insertSelective(Address address) {
        return addressMapper.insertSelective(address);
    }
    /**删除地址*/
    public int deleteByPrimaryKey(Integer id) {
        return addressMapper.deleteByPrimaryKey(id);
    }

    /**修改 也就是保存收货地址*/
    public int updateByPrimaryKeySelective(Address address) {
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    /**根据订单信息的地址ID 查询收货地址*/
    public Address selectByPrimaryKey(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }
}
