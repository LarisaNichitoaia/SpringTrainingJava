package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.controller.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private static final String NO_CUSTOMER_IS_FOUND = "No customer was found!";
    private final CustomerRepository customerRepository;

    public Customer getCustomerById(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            return customer;
        }
        throw new NoSuchObjectException(NO_CUSTOMER_IS_FOUND);
    }
}
