package com.moriawe;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get request for getting all customers in database
    @GetMapping
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}

    // Post request
    @PostMapping
    public void addCustomer(
            @RequestBody NewCustomerRequest request
    ) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    //Delete request
    // Send localhost:8080/api/v1/customers/1 will delete customer Nr 1 (the number after the last /)
    @DeleteMapping("{customerId}")
    public void deleteCustomer(
            @PathVariable("customerId") Integer id
    ) {
        customerRepository.deleteById(id);
    }

    record UpdateCustomerRequest(
            String name,
            String email
    ) {}

    @PutMapping("{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Integer id,
            @RequestBody UpdateCustomerRequest request
    ) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customerRepository.save(customer);
    }


}
