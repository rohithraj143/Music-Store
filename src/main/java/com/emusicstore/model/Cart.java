/**
 * @author Rohith Raj 2/14/2018
 */
package com.emusicstore.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private String cartId;
    private Map<String, CartItem> cartItems;
    private double grandTotal;

    public Cart() {
        cartItems = new HashMap<>();
        grandTotal = 0;
    }

    public Cart(String cartId) {
        this();
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public void addCartItem(CartItem cartItem) {
        String productId = cartItem.getProduct().getProductId();

        if (cartItems.containsKey(productId)) {
            CartItem existingCartItem = cartItems.get(productId);
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
//            existingCartItem.setTotalPrice(existingCartItem.getTotalPrice() + cartItem.getTotalPrice());
            cartItems.put(productId, existingCartItem);
        } else {
            cartItems.put(productId, cartItem);
        }
        updateGrandTotal();
    }

    public void removeCartItem(CartItem cartItem) {
        String productId = cartItem.getProduct().getProductId();
        if (cartItems.containsKey(productId)) {
            cartItems.remove(productId);
        }
        updateGrandTotal();
    }

    public void updateGrandTotal() {
        grandTotal = 0;
        for (CartItem cartItem : cartItems.values()) {
            grandTotal += cartItem.getTotalPrice();
        }
    }
}
