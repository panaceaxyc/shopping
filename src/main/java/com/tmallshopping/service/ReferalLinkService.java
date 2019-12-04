package com.tmallshopping.service;

import com.tmallshopping.entity.ReferalLink;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 23:09
 */
public interface ReferalLinkService {
    /**
     * 返回所有的ReferalLink
     *
     * @return
     */
    List<ReferalLink> listAll();

    /**
     * 更新
     *
     * @param link
     */
    void update(ReferalLink link);
}
