package com.emusicstore.dao.daoImpl;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import java.io.IOException;
import java.util.List;

/**
 * Created by sabin on 2/16/2017.
 */

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getProductLst() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");
        List<Product> products = query.list();
        session.flush();
        return products;
    }


    @Override
    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
    }

    @Override
    public void deleteProduct(String productId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Product where productId = :ID");
        query.setParameter("ID", productId);
        int result = query.executeUpdate();
        if (result > 0) {
            System.out.println("Product deleted");
        }
        session.flush();
    }

    @Override
    public Product getProductById(String productId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.get(Product.class, productId);
        session.flush();
        return product;
    }

    @Override
    public void editProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
    }
}
