package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Address;
import com.tmallshopping.entity.User;
import com.tmallshopping.service.AddressService;
import com.tmallshopping.service.UserService;
import com.tmallshopping.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/19 20:34
 */
@Controller
@RequestMapping("/admin")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @RequestMapping("/address")
    public String address(HttpSession session, Model addressModel){
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/loginPage";
        }

        List<Address> addressList=addressService.getAllAddressByUserId(user.getId());
        addressModel.addAttribute("addressList",addressList);
        return "/address";
    }


    @RequestMapping("/toAddress")
    public String toAddress(Model model){
        List<User> users = userService.list();
        model.addAttribute("users", users);
        return "admin/addressManager";
    }


    @RequestMapping("/listAddress")
    public String listAddress(@RequestParam("userId") String userId, Model model){
        List<Address> addressList=addressService.getAllAddressByUserId(Integer.parseInt(userId));
        model.addAttribute("addressList",addressList);
        return "admin/addressList";
    }




    @RequestMapping("/saveAddr")
    @ResponseBody
    public Msg saveAddr(Address address){
        addressService.updateByPrimaryKeySelective(address);
        return Msg.success("修改成功");
    }

    @RequestMapping("/deleteAddr")
    @ResponseBody
    public Msg deleteAddr(Address address){
        addressService.deleteByPrimaryKey(address.getId());
        return Msg.success("删除成功");
    }

    @RequestMapping("/insertAddr")
    @ResponseBody
    public Msg insertAddr(Address address, HttpServletRequest request){
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        address.setUserId(user.getId());
        addressService.insertSelective(address);
        return Msg.success("添加成功");
    }
}
