package com.tmallshopping.service;

import com.tmallshopping.entity.PropertyValue;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 10:35
 */
public interface PropertyValueService {
    /**
     * 根据product_id返回PropertyValue，
     * 并设置好自身的Property
     *
     * @param product_id
     * @return
     */
    List<PropertyValue> listByProductId(Integer product_id);

    /**
     * 根据 product_id 来删除对应的数据
     *
     * @param product_id
     */
    void deleteByProductId(Integer product_id);

    /**
     * 增加一条属性
     *
     * @param propertyValue
     */
    void add(PropertyValue propertyValue);

    /**
     * 根据id返回一条数据
     *
     * @param id
     */
    PropertyValue get(Integer id);

    /**
     * 根据id删除一条数据
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新一条数据
     *
     * @param propertyValue
     */
    void update(PropertyValue propertyValue);
}
