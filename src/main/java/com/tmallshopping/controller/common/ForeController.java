package com.tmallshopping.controller.common;

import com.github.pagehelper.PageHelper;
import com.tmallshopping.entity.*;
import com.tmallshopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Yuyunkuo
 * @Description: 前台控制器
 * @date 2019/8/16 22:37
 */
@Controller
public class ForeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private AddressService addressService;

    /**
     * 商品详情页面
     * @param model
     * @param product_id
     * @return
     */
    @RequestMapping("/showProduct")
    public String showProduct(Model model, Integer product_id) {
        Product product = productService.get(product_id);
        productService.setReviewCount(product);
        model.addAttribute("product",product);
        List<PropertyValue> propertyValues = propertyValueService.listByProductId(product_id);
        model.addAttribute("propertyValues", propertyValues);
        List<Review> reviews = reviewService.listByProductId(product_id);
        model.addAttribute("reviews", reviews);
        List<ProductImage> productImages = productImageService.list(product_id);
        model.addAttribute("productImages",productImages);
        return "product";
    }

    /**
     * 用户登录
     * @param model
     * @param name
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(Model model,
                        @RequestParam("confirmlogo") String confirmlogo,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session) {
        String code = (String) session.getAttribute("certCode");
        if(!code.equals(confirmlogo)){
            model.addAttribute("msg","验证码错误");
            return "loginPage";
        }
        User user = userService.get(name, password);
        if (null == user) {
            model.addAttribute("msg", "账号密码错误");
            return "loginPage";
        }
        //查询本用户的购物车数量
        Integer cartnumber = userService.selCountCartNumber(user.getId());
        session.setAttribute("cartnumber",cartnumber);
        session.setAttribute("user", user);
        return "redirect:/home";
    }

    /**
     * 用户注册
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model, User user) {
        String name = user.getName();
        boolean exist = userService.isExist(name);
        if (exist) {
            String msg = "用户名已经被占用，不能使用";
            model.addAttribute("msg", msg);
            model.addAttribute("username", user.getName());
            return "registerPage";
        }

        userService.add(new User(null,user.getName(),user.getPassword(),"image/photo.jpg",100));
        return "redirect:/registerSuccessPage";
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("cartnumber");
        return "redirect:/home";
    }

    /**
     * 在添加购物车的时候检查用户是否已经登陆.
     * @param session
     * @return
     * */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user!=null){
            return "success";
        }else{
            return "fail";
        }
    }


    /**
     * 立即购买（即新增OrderItem项）需要考虑以下两种情况：
     * 1.如果这个产品已经存在于购物车中，那么只需要相应的调整数量就可以了
     * 2.如果不存在对应的OrderItem项，那么就新增一个订单项（OrderItem）
     * - 前提条件：已经登录
     *
     * @param product_id 产品的ID
     * @param number     购买的数量
     * @param session    session用于获取user信息
     * @return
     */
    @RequestMapping("/buyone")
    public String buyone(Integer product_id, Integer number, HttpSession session,Model model) {
        Product product = productService.get(product_id);
        int orderItemId = 0;
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/loginPage";
        }
        boolean found = false;
        List<OrderItem> orderItems = orderItemService.listByUserId(user.getId());
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct_id().intValue() == product.getId().intValue()) {
                orderItem.setNumber(orderItem.getNumber() + number);
                orderItemService.update(orderItem);
                orderItemId = orderItem.getId();
                break;
            }
        }
        if (!found) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUser_id(user.getId());
            orderItem.setNumber(number);
            orderItem.setProduct_id(product_id);
            orderItemService.add(orderItem);
            orderItemId = orderItem.getId();
        }
        return "redirect:buy?orderItemId=" + orderItemId;
    }


    @RequestMapping("buy")
    public String buy(Model model, String[] orderItemId, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null) {
            return "loginPage";
        }
        List<OrderItem> orderItems = new ArrayList<>();
        float total = 0;

        for (String strId : orderItemId) {
            int id = Integer.parseInt(strId);
            OrderItem oi = orderItemService.getById(id);
            total += oi.getProduct().getPrice() * oi.getNumber();
            orderItems.add(oi);
        }
        //查询当前用户的收货地址
        List<Address> addressList = addressService.getAllAddressByUserId(user.getId());
        model.addAttribute("address", addressList);
        session.setAttribute("orderItems", orderItems);
        model.addAttribute("total", total);
        return "buyPage";
    }


    @RequestMapping("/createOrder")
    public String createOrder(@RequestParam("addressid") Integer addressid,Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        order.setAddressid(addressid);
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        order.setOrder_code(orderCode);
        order.setCreate_date(new Date());
        order.setUser_id(user.getId());
        order.setStatus(OrderService.waitPay);
        //从session中获取orderItems
        List<OrderItem> orderItems = (List<OrderItem>) session.getAttribute("orderItems");
        float total = orderService.add(order, orderItems);
        return "redirect:payPage?order_id=" + order.getId() + "&total=" + total;
    }


    @RequestMapping("payed")
    public String payed(int order_id, Model model) {
        //Order order = orderService.get(order_id);
        Order order = orderService.getByAddress(order_id);
        order.setStatus(OrderService.waitDelivery);
        order.setPay_date(new Date());
        orderService.update(order);
        model.addAttribute("o", order);
        return "payed";
    }


    /**
     * 加入购物车方法，跟buyone()方法有些类似，但返回不同
     * 仍然需要新增订单项OrderItem，考虑两个情况：
     * 1.如果这个产品已经存在于购物车中，那么只需要相应的调整数量就可以了
     * 2.如果不存在对应的OrderItem项，那么就新增一个订单项（OrderItem）
     * - 前提条件：已经登录
     *
     * @param product_id
     * @param num
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("addCart")
    @ResponseBody
    public String addCart(int product_id, int num, Model model, HttpSession session) {
        Product p = productService.get(product_id);
        User user = (User)session.getAttribute("user");
        boolean found = false;
        List<OrderItem> ois = orderItemService.listByUserId(user.getId());
        for (OrderItem oi :ois) {
            if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
                oi.setNumber(oi.getNumber() + num);
                orderItemService.update(oi);
                found = true;
                break;
            }
        }

        if (!found) {
            OrderItem oi = new OrderItem();
            oi.setUser_id(user.getId());
            oi.setNumber(num);
            oi.setProduct_id(product_id);
            orderItemService.add(oi);
        }
        return "success";
    }
    /**
     * 查看购物车方法：
     * 1.首先通过session获取到当前的用户
     * 2.获取这个用户关联的订单项的集合
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/cart")
    public String cart(Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/loginPage";
        }
         List<OrderItem> orderItems = orderItemService.listForCart(user.getId());
        //List<OrderItem> orderItems = orderItemService.selCartList(user.getId());
        model.addAttribute("orderItems", orderItems);
        return "cart";
    }

    /**
     * 删除订单项
     * @param model
     * @param session
     * @param orderItemId
     * @return
     */
    @RequestMapping("deleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(Model model, HttpSession session, Integer orderItemId) {
        User user = (User) session.getAttribute("user");
        if (null == user)
            return "fail";
        orderItemService.delete(orderItemId);
        return "success";
    }

    /**
     * 查看已买到的宝贝
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/bought")
    public String bought(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/loginPage";
        }
        List<Order> orders = orderService.list(user.getId(), OrderService.delete);
        orderItemService.fill(orders);
        model.addAttribute("orders", orders);
        return "/bought";
    }

    /**
     * 删除订单
     * @param model
     * @param order_id
     * @return
     */
    @RequestMapping("deleteOrder")
    @ResponseBody
    public String deleteOrder(Model model, Integer order_id) {
        Order o = orderService.get(order_id);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return "success";
    }

    /**
     * 商品评价
     * @param model
     * @param order_id
     * @return
     */
    @RequestMapping("review")
    public String review(Model model, Integer order_id) {
        Order order = orderService.get(order_id);
        orderItemService.fill(order);
        Product product = order.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.listByProductId(product.getId());
        productService.setReviewCount(product);
        model.addAttribute("product", product);
        model.addAttribute("order", order);
        model.addAttribute("reviews", reviews);
        return "reviewPage";
    }


    /**
     *  评价
     * @param model
     * @param session
     * @param order_id
     * @param product_id
     * @param content
     * @return
     */
    @RequestMapping("doreview")
    public String doreview(Model model, HttpSession session,
                           @RequestParam("order_id") Integer order_id,
                           @RequestParam("product_id") Integer product_id,
                           String content) {
        Order order = orderService.get(order_id);
        order.setStatus(OrderService.finish);
        orderService.update(order);
        User user = (User) session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setUser_id(user.getId());
        review.setProduct_id(product_id);
        review.setCreateDate(new Date());
        reviewService.add(review);
        return "redirect:review?order_id=" + order_id + "&showonly=true";
    }

    /**
     * 搜索 商品
     * @param model
     * @param keyword
     * @return
     */
    @RequestMapping("/searchProduct")
    public String searchProduct(Model model, String keyword) {
        PageHelper.offsetPage(0, 20);
        List<Product> products = productService.search(keyword);
        for(Product product:products) {
            product.setReviewCount(reviewService.getCount(product.getId()));
        }
        model.addAttribute("products",products);
        return "searchResult";
    }

    /**
     * 按照商品的 销量 人气 价格 升序排序 功能
     * @param model
     * @param sort
     * @param keyword
     * @return
     */
    @RequestMapping("sortProduct")
    public String sortProduct(Model model, String sort, String keyword) {
        List<Product> products = productService.search(keyword);
        for(Product product : products) {
            product.setReviewCount(reviewService.getCount(product.getId()));
        }

        if(sort!=null) {
            switch (sort) {
                case "all" :
                    Collections.sort(products, Comparator.comparing(Product::getSaleXReviewCount));
                    break;
                case "reviewCount":
                    Collections.sort(products, Comparator.comparing(Product::getReviewCount));
                    break;
                case "date":
//					Collections.sort(products, comparing(Product::get));
                    break;
                case "sale":
                    Collections.sort(products, Comparator.comparing(Product::getSale));
                    break;
                case "price":
                    Collections.sort(products, Comparator.comparing(Product::getPrice));
                    break;
            }
        }
        model.addAttribute("products", products);
        return "searchResult";
    }

    @RequestMapping("confirmPay")
    public String confirmPay(Model model, Integer order_id) {
        Order order = orderService.get(order_id);
        orderItemService.fill(order);
        model.addAttribute("order", order);
        return "confirmPay";
    }

    @RequestMapping("orderConfirmed")
    public String orderConfirmed(Model model, Integer order_id) {
        Order o = orderService.get(order_id);
        o.setStatus(OrderService.waitReview);
        o.setConfirm_date(new Date());
        orderService.update(o);
        return "orderConfirmedPage";
    }
}
