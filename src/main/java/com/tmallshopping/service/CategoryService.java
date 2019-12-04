package com.tmallshopping.service;

import com.tmallshopping.entity.Category;
import com.tmallshopping.entity.CategoryExample;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 22:39
 */
public interface CategoryService {
    /**
     * 返回分类列表
     * @return
     */
    List<Category> list();

    /**
     * 更新分类
     * @param category
     * @return
     */
    void update(Category category);

    /**
     * 通过id获取对应的数据
     * @param id
     * @return
     */
    Category get(Integer id);


    public List<Category> selectByExample(CategoryExample example);
}
