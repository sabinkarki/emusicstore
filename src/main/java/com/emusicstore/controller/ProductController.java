package com.emusicstore.controller;

import com.emusicstore.domain.Product;
import com.emusicstore.enums.ProductCategory;
import com.emusicstore.enums.ProductCondition;
import com.emusicstore.enums.ProductStatus;
import com.emusicstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sabin on 2/15/2017.
 */

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private Path path;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }


    @RequestMapping(value = "/allProducts", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        List<Product> products = productService.getProductLst();
        model.addAttribute("products", products);
        return "productList";
    }

    @RequestMapping(value = "/allProducts/viewProduct/{productId}")
    public String getProductDetailsPage(@PathVariable String productId, Model model) throws IOException {
        Product product = productService.getProductById(productId);
        model.addAttribute(product);
        return "viewProduct";
    }

    @RequestMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/admin/productInventory",method=RequestMethod.GET)
    public String productInventory(Model model) {
        List<Product> products = productService.getProductLst();
        model.addAttribute("products", products);
        return "productInventory";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method =RequestMethod.GET)
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        List<ProductCategory> lstOfCategory = new ArrayList<ProductCategory>(Arrays.asList(ProductCategory.values()));
        List<ProductCondition> lstOfProductCondition = new ArrayList<ProductCondition>(Arrays.asList(ProductCondition.values()));
        List<ProductStatus> lstOfProductStatus = new ArrayList<ProductStatus>(Arrays.asList(ProductStatus.values()));
        model.addAttribute("lstOfProductCondition", lstOfProductCondition);
        model.addAttribute(lstOfCategory);
        model.addAttribute("lstOfProductStatus", lstOfProductStatus);
        return "addProduct";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product , HttpServletRequest request) {

        MultipartFile productImage=product.getProductImage();
        String rootDirectory=request.getSession().getServletContext().getRealPath("/");
        path= Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\productImages\\"+product.getProductId()+".png");

        productService.addProduct(product);
        if(productImage!=null || !productImage.isEmpty()){
            try{
                  productImage.transferTo(new File(path.toString()));
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Product Image saving failed " ,e);

            }
        }
        return "redirect:/admin/productInventory";
    }

    @RequestMapping(value="/admin/productInventory/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") String productId){
        productService.deleteProduct(productId);
        return "redirect:/admin/productInventory";
    }
}
