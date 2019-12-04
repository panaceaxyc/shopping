package com.tmallshopping.service.impl;

import com.tmallshopping.entity.Admin;
import com.tmallshopping.mapper.AdminMapper;
import com.tmallshopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/20 10:48
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selByLoginCheck(String username, String password) {
        return adminMapper.selByLoginCheck(username,password);
    }
}
