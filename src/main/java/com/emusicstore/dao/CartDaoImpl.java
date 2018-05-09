/**
 * @author Rohith Raj 2/14/2018
 */
package com.emusicstore.dao;

import com.emusicstore.model.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Map<String, Cart> listOfCarts;

    public CartDaoImpl() {
        listOfCarts = new HashMap<>();
    }

    @Override
    public Cart create(Cart cart) {

        if (listOfCarts.keySet().contains(cart.getCartId())) {
            throw new IllegalArgumentException(
                    "Cannot create a cart. A cart with the given id already exists");
        }
        listOfCarts.put(cart.getCartId(), cart);
        return cart;
    }

    @Override
    public Cart read(String cartId) {
        return listOfCarts.get(cartId);
    }

    @Override
    public void update(String cartId, Cart cart) {
        if (!listOfCarts.keySet().contains(cart.getCartId())) {
            throw new IllegalArgumentException(
                    "Cannot update cart. Cart with the given id doesn't exists");
        }
        listOfCarts.put(cartId, cart);
    }

    @Override
    public void delete(String cartId) {
        if (!listOfCarts.keySet().contains(cartId)) {
            throw new IllegalArgumentException(
                    "Cannot delete cart. Cart with the given id doesn't exists");
        }
        listOfCarts.remove(cartId);
    }
}
