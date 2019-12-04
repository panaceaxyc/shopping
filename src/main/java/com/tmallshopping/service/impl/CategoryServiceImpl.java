package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Category;
import com.tmallshopping.entity.CategoryExample;
import com.tmallshopping.mapper.CategoryMapper;
import com.tmallshopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 22:39
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> list() {
        CategoryExample example =new CategoryExample();
        List<Category> categories = categoryMapper.selectByExample(example);
        return categories;
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public Category get(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> selectByExample(CategoryExample example) {
        return categoryMapper.selectByExample(example);
    }


}
