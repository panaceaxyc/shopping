package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Property;
import com.tmallshopping.entity.PropertyExample;
import com.tmallshopping.mapper.PropertyMapper;
import com.tmallshopping.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/17 10:39
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;

    public Property get(Integer id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Property> list(Integer category_id) {
        PropertyExample example = new PropertyExample();
        example.or().andCategory_idEqualTo(category_id);
        List<Property> properties = propertyMapper.selectByExample(example);
        return properties;
    }

    @Override
    public void add(Property property) {
        propertyMapper.insert(property);
    }

    @Override
    public void delete(Integer id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        propertyMapper.updateByPrimaryKey(property);
    }
}
