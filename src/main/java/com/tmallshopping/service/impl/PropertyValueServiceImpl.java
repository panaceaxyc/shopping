package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Property;
import com.tmallshopping.entity.PropertyValue;
import com.tmallshopping.entity.PropertyValueExample;
import com.tmallshopping.mapper.PropertyValueMapper;
import com.tmallshopping.service.ProductService;
import com.tmallshopping.service.PropertyService;
import com.tmallshopping.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 10:36
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private ProductService productService;

    public List<PropertyValue> listByProductId(Integer product_id) {
        PropertyValueExample example = new PropertyValueExample();
        example.or().andProduct_idEqualTo(product_id);
        List<PropertyValue> propertyValues = propertyValueMapper.selectByExample(example);
        for (PropertyValue propertyValue : propertyValues){
            Property property =  propertyService.get(propertyValue.getProduct_id());
            propertyValue.setProperty(property);
        }
        return propertyValues;
    }

    @Override
    public void deleteByProductId(Integer product_id) {
        // 按条件查询出需要删除的列表
        PropertyValueExample example = new PropertyValueExample();
        example.or().andProduct_idEqualTo(product_id);
        Integer category_id = productService.get(product_id).getCategory_id();
        List<PropertyValue> propertyValues = list(product_id, category_id);
        // 循环删除
        for (int i = 0; i < propertyValues.size(); i++) {
            propertyValueMapper.deleteByPrimaryKey(propertyValues.get(i).getId());
        }
    }

    @Override
    public void add(PropertyValue propertyValue) {
        propertyValueMapper.insert(propertyValue);
    }

    @Override
    public PropertyValue get(Integer id) {
        return propertyValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        propertyValueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKey(propertyValue);
    }

    public List<PropertyValue> list(Integer product_id, Integer category_id) {
        PropertyValueExample example = new PropertyValueExample();
        List<PropertyValue> propertyValues = new ArrayList<>();
        List<Property> properties = propertyService.list(category_id);
        for (Property property : properties) {
            // 筛选出同时匹配 property_id 和 product_id 的值
            example.or().andProperty_idEqualTo(property.getId()).andProduct_idEqualTo(product_id);
            propertyValues.addAll(propertyValueMapper.selectByExample(example));
        }
        return propertyValues;
    }
}
