package com.tmallshopping.service.impl;

import com.tmallshopping.entity.ReferalLink;
import com.tmallshopping.entity.ReferalLinkExample;
import com.tmallshopping.mapper.ReferalLinkMapper;
import com.tmallshopping.service.ReferalLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/16 23:09
 */
@Service
public class ReferalLinkServiceImpl implements ReferalLinkService {

    @Autowired
    private ReferalLinkMapper referalLinkMapper;

    /**
     * 返回所有的ReferalLink
     *
     * @return
     */
    public List<ReferalLink> listAll() {
        ReferalLinkExample example = new ReferalLinkExample();
        return referalLinkMapper.selectByExample(example);
    }

    @Override
    public void update(ReferalLink link) {
        referalLinkMapper.updateByPrimaryKey(link);
    }
}
