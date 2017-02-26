package com.emusicstore.dao;

import com.emusicstore.domain.Product;

import java.io.IOException;
import java.util.List;

/**
 * Created by sabin on 2/17/2017.
 */
public interface ProductDao {
    public List<Product> getProductLst();
    public Product getProductById(String productId) throws IOException;
    public void addProduct(Product product);
    public void deleteProduct(String productId);
}
