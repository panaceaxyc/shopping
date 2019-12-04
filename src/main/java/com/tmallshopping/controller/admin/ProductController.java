package com.tmallshopping.controller.admin;

import com.tmallshopping.entity.Admin;
import com.tmallshopping.entity.Category;
import com.tmallshopping.entity.Product;
import com.tmallshopping.entity.ProductImage;
import com.tmallshopping.service.CategoryService;
import com.tmallshopping.service.ProductImageService;
import com.tmallshopping.service.ProductService;
import com.tmallshopping.service.PropertyValueService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Yuyunkuo
 * @Description:
 * @date 2019/8/18 9:12
 */
@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private PropertyValueService propertyValueService;

    /**
     * 查询所有的产品
     * @param model
     * @param category_id
     * @return
     */

    @RequestMapping("/listProduct")
    public String list(Model model, Integer category_id, HttpSession session) {
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin==null) {
            return "admin/loginPage";
        }
        List<Product> products = productService.list(category_id);
        model.addAttribute("products", products);
        Category category = categoryService.get(category_id);
        model.addAttribute("category", category);
        return "admin/listProduct";
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    @RequestMapping("/addProduct")
    public String add(Product product,
                      @RequestParam MultipartFile[] fileToUpload,
                      HttpServletRequest request,
                      Model registerResult) throws IOException {
        //如下为上传封面图
        String fileName = UUID.randomUUID().toString() + product.getFile().getOriginalFilename()
                .substring(product.getFile().getOriginalFilename().lastIndexOf("."));

        //String path = "D:/idea-workspace/tmallshopping/src/main/resources/static/img/upload/"+fileName;
        //String path = "D:/images/"+fileName;
        String returnUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/";
        String path = request.getSession().getServletContext().getRealPath("upload") +"\\"+fileName;
        /**
         * 原来
         * // 上传文件到指定位置
         * 		String filePath = request.getSession().getServletContext()
         * 				.getRealPath("img/product/" + product_id);
         */
        //String path = request.getSession().getServletContext().getRealPath("static/upload/"+fileName);
         System.out.println("-------------"+path+"-------------");
         System.out.println("-------------"+returnUrl+"-------------");
        //System.out.println("%%%%%%%%%%%%%%%%%"+"upload/"+fileName+"%%%%%%%%%%%%%%%%%%%%%");
        try {
            FileUtils.copyInputStreamToFile(product.getFile().getInputStream(), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //product.setImages("upload/"+fileName);
        product.setImages(returnUrl+fileName);
        productService.add(product);
        //为上传小图(4张)
        for(MultipartFile multipartFile:fileToUpload) {
            if (multipartFile != null) {
                String imageNames = UUID.randomUUID().toString()  + multipartFile.getOriginalFilename()
                        .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                //String imagePath = "D:/idea-workspace/tmallshopping/src/main/resources/static/img/upload/"+imageNames;
                String imagePath = "D:/idea-workspace/tmallshopping/src/main/resources/static/img/upload/"+imageNames;
                productImageService.add(new ProductImage(null,product.getId(),"upload/"+imageNames));
                //存图片
                multipartFile.transferTo(new File(imagePath));
            }
        }
        return "redirect:/admin/listProduct?category_id=" + product.getCategory_id();
        //return "redirect:/home";
    }



    @RequestMapping("/deleteProduct")
    public String delete(Integer id, HttpServletRequest request) {
        // 在删除产品的时候将对应的 5 个 ProductImage 数据也删除了
        productImageService.deleteByProductId(id);
        String path = request.getSession().getServletContext().getRealPath("" + id);
        deleteDir(new File(path));
        // 删除外键对应的数据
        propertyValueService.deleteByProductId(id);

        int category_id = productService.get(id).getCategory_id();
        productService.delete(id);
        return "redirect:/admin/listProduct?category_id=" + category_id;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    @RequestMapping("/editProduct")
    public String edit(Integer id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        Category category = categoryService.get(product.getCategory_id());
        model.addAttribute("category", category);
        return "admin/editProduct";
    }

    @RequestMapping("/updateProduct")
    public String update(Product product) {
        productService.update(product);
        return "redirect:/admin/listProduct?category_id=" + product.getCategory_id();
    }
}
