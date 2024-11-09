package com.example.demo.services;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);// Takes a purchase and understandably must return a PurchaseResponse object
    // This Purchase response object will later be returned to
    // The controller, which will then return it to the front end.
    // This object will hold the getters/setters and value for the
    // tracking number (the tracking number will be set in the
    // checkout service implementation)
}

/* This comment is just here for me to write out my understanding
* of why I need this. It looks like technically I don't, but creating
* this interface is best practice because it allows for 'loose
* coupling' and 'flexibility.' This interface is useful because in
* the future if I wanted to add a different checkoutService, rather
* than write the same code again, or use code from another class,
* I can just have the service implement this interface. It seems this
* interface is good for requiring that all services use the defined
* method within here, which will be methods that ALL checkout services
* must have. Currently the only must-have method all checkout services
* should have is placeOrder() */