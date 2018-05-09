/**
 * @author Rohith Raj 2/14/2018
 */
package com.emusicstore.controller;

import com.emusicstore.dao.CartDao;
import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Cart;
import com.emusicstore.model.CartItem;
import com.emusicstore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rest/cart")
public class CartController {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping("/{cartId}")
    public @ResponseBody Cart read(@PathVariable("/cartId") String cartId) {
        return cartDao.read(cartId);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable("cartId") String cartId,
                       @RequestBody Cart cart) {
        cartDao.update(cartId, cart);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("cartId") String cartId) {
        cartDao.delete(cartId);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable("productId") String productId,
                        HttpServletRequest request) {

        String sessionId = request.getSession().getId();
        Cart cart = cartDao.read(sessionId);

        if (cart == null) {
            cart = cartDao.create(new Cart(sessionId));
        }
        Product product = productDao.getProductById(productId);

        if (product == null) {
            throw new IllegalArgumentException(new Exception());
        }
        cart.addCartItem(new CartItem(product));
        cartDao.update(sessionId, cart);
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable("productId") String productId,
                           HttpServletRequest request) {

        String sessionId = request.getSession().getId();
        Cart cart = cartDao.read(sessionId);

        if (cart == null) {
            cart = cartDao.create(new Cart(sessionId));
        }
        Product product = productDao.getProductById(productId);

        if (product == null) {
            throw new IllegalArgumentException(new Exception());
        }
        cart.removeCartItem(new CartItem(product));
        cartDao.update(sessionId, cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload!")
    public void handleClientError(Exception e) {

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server error")
    public void handleServerError(Exception e) {

    }

}
