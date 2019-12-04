package com.tmallshopping.service.impl;

import com.tmallshopping.entity.User;
import com.tmallshopping.entity.UserExample;
import com.tmallshopping.mapper.UserMapper;
import com.tmallshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 23:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(int user_id) {
        return userMapper.selectByPrimaryKey(user_id);
    }

    public User get(String name, String password) {
        UserExample example = new UserExample();
        example.or().andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> result = userMapper.selectByExample(example);
        if(result.isEmpty()){
            return null;
        }else{
            return result.get(0);
        }
    }

    public boolean isExist(String name) {
        UserExample example = new UserExample();
        example.or().andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if(!users.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> list() {
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public void updatePassword(Integer id, String password) {
        User user = get(id);
        user.setPassword(password);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void updatePoints(Integer id, Integer points) {
        User user = get(id);
        user.setPoints(user.getPoints()+3);
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public Integer selCountCartNumber(Integer user_id) {
        return userMapper.selCountCartNumber(user_id);
    }
}
