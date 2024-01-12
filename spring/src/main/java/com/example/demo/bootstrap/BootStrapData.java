package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Division division = new Division();

        division.setId(65L);

        Customer mike = new Customer("Mike", "Smith", "423 pony ln", "22312", "2134321534", division );
        mike.getDivision().add(division);
        division.getCustomers().add(mike);

        Customer john = new Customer("Peter", "Wilks", "123 Main St", "12345", "9876543210", division);
        john.getDivision().add(division);
        division.getCustomers().add(john);

        Customer alice = new Customer("Alice", "Johnson", "456 Oak St", "67890", "1234567890", division);
        alice.getDivision().add(division);
        division.getCustomers().add(alice);

        Customer bob = new Customer("Bob", "Williams", "789 Pine St", "54321", "9876123450", division);
        bob.getDivision().add(division);
        division.getCustomers().add(bob);

        Customer emily = new Customer("Emily", "Davis", "101 Cedar St", "98765", "1234987650", division);
        emily.getDivision().add(division);
        division.getCustomers().add(emily);

        customerRepository.save(mike);
        customerRepository.save(bob);
        customerRepository.save(alice);
        customerRepository.save(john);
        customerRepository.save(emily);


        List<Customer> allCustomers = customerRepository.findAll();

    }
}
