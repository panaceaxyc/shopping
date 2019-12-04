package com.tmallshopping.service;

import com.tmallshopping.entity.Admin;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/20 10:48
 */
public interface AdminService {

    /**
     * 查询 验证登录
     * @param username
     * @param password
     * @return
     */
    Admin selByLoginCheck(String username, String password);
}
