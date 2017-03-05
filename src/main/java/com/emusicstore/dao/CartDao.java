package com.emusicstore.dao;

import com.emusicstore.domain.Cart;

/**
 * Created by sabin on 3/5/2017.
 */
public interface CartDao {
    public Cart createCart(Cart cart);
    public Cart read();
    public void update(String cartId);
    public void delete(String cartId);
}
