package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import lombok.Data;
import java.util.Set;

@Data
public class Purchase {

    private Cart cart;
    private Set<CartItem> cartItems;

}



/* This comment is just for me to understand the neccesity of this file.
* This file seems pretty straight forward as it holds the data for the
* purchase. I was instructed to only have cart and items, but it seemed
* important to include the customer that used/purchased them so I included
* that as well. From my understanding, this class is mainly just a service
* for interacting with the data before storing it in the database. From my
* research it seems the point of this is to add logic/seperation between the
* database and controller. It seems like in a real application there
* would be more logic/code here  */
