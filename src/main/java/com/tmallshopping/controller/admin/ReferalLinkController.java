package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.ReferalLink;
import com.tmallshopping.service.ReferalLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/18 10:05
 */
@Controller
@RequestMapping("/admin")
public class ReferalLinkController {
    /**
     * 注入
     */
    @Autowired
    private ReferalLinkService referalLinkService;

    @RequestMapping("/listLink")
    public String list(Model model) {
        List<ReferalLink> links = referalLinkService.listAll();
        model.addAttribute("links",links);
        return "admin/listLink";
    }

    @RequestMapping("/updateLink")
    public String update(ReferalLink link) {
        referalLinkService.update(link);
        return "redirect:/admin/listLink";
    }
}
