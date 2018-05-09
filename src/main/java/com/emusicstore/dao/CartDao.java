package com.emusicstore.dao;

import com.emusicstore.model.Cart;

/**
 * @author Rohith Raj 2/14/2018
 */
public interface CartDao {

    Cart create(Cart cart);

    Cart read(String cartId);

    void update(String cartId, Cart cart);

    void delete(String cartId);

}
