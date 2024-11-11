package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
@Service
public class CheckoutServiceImpl implements CheckoutService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();
        Set<CartItem> cartItems = purchase.getCartItems();

        cartItems.forEach(item -> item.setCart(cart));

        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        cartRepository.save(cart);
        cartItemRepository.saveAll(cartItems);

        return new PurchaseResponse(orderTrackingNumber);
    }
}

/* This is the actual service for the checkout */

