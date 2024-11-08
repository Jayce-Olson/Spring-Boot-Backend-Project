package services;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PurchaseResponse {
    private String orderTrackingNumber;
}
/* This Comment is just to help me understand the why and Architecutre
* of this code. It seems that this class is just for responding to the
* frontend (which makes sense with the name) and it seems the only piece
* of information we are responding with is a tracking number for the
* purchase. It doesn't seem like a lot but the @data annotation adds
* getters/setters for orderTrackingNumber. It seems the point of this
* class is just to seperate the "response" service */