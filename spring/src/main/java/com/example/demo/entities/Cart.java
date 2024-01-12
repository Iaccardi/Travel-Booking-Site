package com.example.demo.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="carts")
public class Cart {


        @Column(name = "cart_id", nullable = false, unique = true)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long id;


        @Column(name = "order_tracking_number")
        private String orderTrackingNumber;

        @Column(name = "package_price")
        private BigDecimal package_price;

        @Column(name = "party_size")
        private int party_size;

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private StatusType status;

        @Column(name = "create_date")
        @CreationTimestamp
        private Date create_date;

        @Column(name = "last_update")
        @UpdateTimestamp
        private Date last_update;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<CartItem> cartItem = new HashSet<>();

        public void add(CartItem cartItem) {
                this.cartItem.add(cartItem);
        }

        public void setCartItems(Set<CartItem> cartItems) {
                this.cartItem = cartItems;
        }
}
