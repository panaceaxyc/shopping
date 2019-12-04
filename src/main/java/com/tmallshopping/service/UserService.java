package com.tmallshopping.service;

import com.tmallshopping.entity.User;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 23:02
 */
public interface UserService {
    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User get(int id);
    /**
     * 根据用户名和密码来查询用户
     *
     * @param name
     * @param password
     * @return
     */
    User get(String name, String password);

    /**
     * 根据用户名判断用户是否存在
     *
     * @param name
     * @return
     */
    boolean isExist(String name);

    /**
     * 增加一条用户数据
     *
     * @param user
     */
    void add(User user);

    /**
     * 返回所有的用户
     *
     * @return
     */
    List<User> list();

    /**
     * 更改用户密码
     *
     * @param id
     * @param password
     */
    void updatePassword(Integer id, String password);

    /**
     * 更新会员积分 +3分
     * @param id
     * @param points
     */
    void updatePoints(Integer id,Integer points);

    /**
     * 查询本用户的购物车数量
     * @param user_id
     * @return
     */
    Integer selCountCartNumber(Integer user_id);
}
