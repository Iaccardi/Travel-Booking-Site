package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository){
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    };


    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        System.out.println("Inside placeOrder method");

        Cart cart = purchase.getCart();

        String orderTrackingNumber = generateOrderTrackingNumber();

        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();


        cartItems.forEach(item -> item.setCart(cart));

        cartItems.forEach(item -> cart.add(item));


        cart.setCartItems(purchase.getCartItems());
        cart.setCustomer(purchase.getCustomer());

        Customer customer = purchase.getCustomer();

        customer.add(cart);
        cart.setStatus(StatusType.ordered);


        cartRepository.save(cart);

        //customerRepository.save(customer);


        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        String orderTrackingNumber = UUID.randomUUID().toString();
        return orderTrackingNumber;
    }
}
