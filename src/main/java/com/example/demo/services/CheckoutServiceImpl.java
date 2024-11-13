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
import java.util.function.Supplier;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
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
        Set<CartItem> cartItems = purchase.getCartItems();

        String orderTrackingNumber = UUID.randomUUID().toString();
        // Set all of the data areas
        cart.setOrderTrackingNumber(orderTrackingNumber);
        cart.setStatus(StatusType.ordered);
        cart.setPackagePrice(new BigDecimal(0)); // Setting the value to zero now so there won't be an error calculatiing later from null value

        cartItems.forEach(cartItem -> { // Iterate through Items to add to cart
            cart.addCartItem(cartItem);
            System.out.println(cartItem.getExcursions());
            //cart.setPackagePrice(price.get().add(cartItem.getVacation().getTravel_price())); // find the travel price of the vacation an add it to the carts price area
            cartItem.getExcursions().forEach(excursion -> {
                // Add excursions prices
                cart.setPackagePrice(cart.getPackagePrice().add(excursion.getExcursion_price()));
            });
            // add vacations prices
            cart.setPackagePrice(cart.getPackagePrice().add(cartItem.getVacation().getTravel_price()));
            cartItem.setCart(cart);
        });
        System.out.println(cart.getPackagePrice());
        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }
}


/* This is the actual service for the checkout */

