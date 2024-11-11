package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart = purchase.getCart();
        Customer customer = purchase.getCustomer();
        Set<CartItem> cartItems = purchase.getCartItems();

        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        cart.setStatus(StatusType.ordered);

        cartItems.forEach(cartItem -> {
            cart.addCartItem(cartItem);
            cartItem.setCart(cart);
        });

        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }
}


/* This is the actual service for the checkout */

