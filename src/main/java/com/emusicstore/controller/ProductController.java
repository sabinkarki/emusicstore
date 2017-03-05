package com.emusicstore.controller;

import com.emusicstore.domain.Product;
import com.emusicstore.enums.ProductCategory;
import com.emusicstore.enums.ProductCondition;
import com.emusicstore.enums.ProductStatus;
import com.emusicstore.service.ProductService;
import com.emusicstore.springvalidator.ProductSpringValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    /*@InitBinder()
    protected void initUserBinder(WebDataBinder binder) {
        binder.setValidator(new ProductSpringValidator());
      }*/

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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/admin/productInventory", method = RequestMethod.GET)
    public String productInventory(Model model) {
        List<Product> products = productService.getProductLst();
        model.addAttribute("products", products);
        return "productInventory";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.GET)
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        addListToShowInJsp(model);
        return "addProduct";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result,
                                 HttpServletRequest request, Model model) {

        ProductSpringValidator validator =new ProductSpringValidator();
        validator.validate(product,result);

        if (result.hasErrors()) {
            addListToShowInJsp(model);
            return "addProduct";
        }
        MultipartFile productImage = product.getProductImage();
        productService.addProduct(product);
        Path path = getPath(request, product.getProductId());
        if (productImage != null || !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product Image saving failed ", e);
            }
        }
        return "redirect:/admin/productInventory";
    }

    @RequestMapping(value = "/admin/productInventory/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") String productId, HttpServletRequest request) {
        Path path = getPath(request, productId);
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        productService.deleteProduct(productId);
        return "redirect:/admin/productInventory";
    }

    @RequestMapping(value = "/admin/productInventory/editProduct/{productId}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("productId") String productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        addListToShowInJsp(model);
        return "editProduct";
    }

    @RequestMapping(value = "/admin/productInventory/editProduct", method = RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result, HttpServletRequest request,Model model) {
        ProductSpringValidator validator =new ProductSpringValidator();
        validator.validate(product,result);
        if (result.hasErrors()) {
            addListToShowInJsp(model);
            return "editProduct";
        }

        MultipartFile productImage = product.getProductImage();
        Path path = getPath(request, product.getProductId());

        if ((productImage != null || !productImage.isEmpty())) {
            if (productImage.getSize() != 0) {
                try {
                    productImage.transferTo(new File(path.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Product Image saving failed ", e);
                }
            }
        }
        productService.editProduct(product);
        return "redirect:/admin/productInventory";
    }

    private Path getPath(HttpServletRequest request, String productId) {
        Path path1;
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path1 = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\productImages\\"+ productId + ".png");
        return path1;
    }

    private void addListToShowInJsp(Model model) {
        List<ProductCategory> lstOfCategory = new ArrayList<ProductCategory>(Arrays.asList(ProductCategory.values()));
        List<ProductCondition> lstOfProductCondition = new ArrayList<ProductCondition>(Arrays.asList(ProductCondition.values()));
        List<ProductStatus> lstOfProductStatus = new ArrayList<ProductStatus>(Arrays.asList(ProductStatus.values()));
        model.addAttribute("lstOfProductCondition", lstOfProductCondition);
        model.addAttribute(lstOfCategory);
        model.addAttribute("lstOfProductStatus", lstOfProductStatus);
    }
}
