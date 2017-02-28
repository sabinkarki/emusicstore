package com.emusicstore.service.serviceImpl;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.domain.Product;
import com.emusicstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by sabin on 2/17/2017.
 */

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProductLst() {
        return productDao.getProductLst();

    }

    @Override
    public Product getProductById(String productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    public void editProduct(Product product) {
        productDao.editProduct(product);
    }
}
