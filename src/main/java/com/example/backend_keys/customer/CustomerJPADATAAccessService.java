package com.example.backend_keys.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerJPADATAAccessService implements CustomerDao {
    private final CustomerRepisotory customerRepisotory;

    public CustomerJPADATAAccessService(CustomerRepisotory customerRepisotory) {
        this.customerRepisotory = customerRepisotory;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customerRepisotory.findAll();
    }

    @Override
    public Optional<Customer> selectAllCustomerById(Integer id) {
        return customerRepisotory.findById(id);
    }

    @Override
    public void insertCostumer(Customer customer) {
        customerRepisotory.save(customer);
    }

    @Override
    public boolean existPersonwithEmail(String email) {
        return customerRepisotory.existsCustomerByEmail(email);
    }

    @Override
    public boolean existPersonWithId(Integer id) {
        return customerRepisotory.existsCustomerById(id);
    }

    @Override
    public void deleteCustomer(Integer id) {
         customerRepisotory.deleteById(id);
    }

    @Override
    public void updateCustomer(Customer updateCustomer) {
        customerRepisotory.save(updateCustomer);
    }
}
