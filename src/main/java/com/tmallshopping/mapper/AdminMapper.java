package com.tmallshopping.mapper;

import com.tmallshopping.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/20 10:53
 */
public interface AdminMapper {
     Admin selByLoginCheck(@Param("username") String username,@Param("password") String password);
}
