package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="divisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Division {

    @Id
    @Column(name="division_id")
    private Long id;


    @Column(name="division")
    private String division_name;

    @CreationTimestamp
    @Column(name="create_date")
    private Date create_date;

    @UpdateTimestamp
    @Column(name="last_update")
    private Date last_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false, nullable = false)
    private Country country;

    @OneToMany(mappedBy = "division", cascade = CascadeType.PERSIST)
    private Set<Customer> customers = new HashSet<>();

    @Column(name="country_id")
    private Long country_id;

    public void setCountry(Country country) {
        setCountry_id(country.getId());
        this.country = country;
    }


    public void addCustomer(Customer customer) {
        if (!this.customers.contains(customer)) {
            this.customers.add(customer);
            customer.setDivision(this);
        }
    }

    public void add(Division division) {
    }
}
