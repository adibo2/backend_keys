package com.example.backend_keys.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectAllCustomerById(Integer id);
    void insertCostumer(Customer customer);
    boolean existPersonwithEmail(String email);
    boolean existPersonWithId(Integer id);

    void deleteCustomer(Integer id);

    void updateCustomer(Customer updateCustomer);


}
