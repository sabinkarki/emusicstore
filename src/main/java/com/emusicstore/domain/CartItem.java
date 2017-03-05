package com.emusicstore.domain;

/**
 * Created by sabin on 3/5/2017.
 */
public class CartItem {

    private  Product product;
    private int quantity;
    private double totalPrice;

    public CartItem(){};

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartItem(Product product, int quantity, double totalPrice) {
        this.product = product;
        this.quantity=quantity;
        this.totalPrice=totalPrice;

    }
}
