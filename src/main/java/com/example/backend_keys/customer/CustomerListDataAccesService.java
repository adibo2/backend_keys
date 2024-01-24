package com.example.backend_keys.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccesService implements CustomerDao {

    private static List<Customer> customers;
    static {
        customers=new ArrayList<>();
        Customer alex=new Customer(1,"Alex","alex@gmail.com",21);
        Customer jamila=new Customer(2,"Jamila","jamila@gmail.com",21);

        customers.add(alex);
        customers.add(jamila);

    }
    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public boolean existPersonwithEmail(String email) {
        return customers.stream().anyMatch(c->c.getEmail().equals(email));
    }

    @Override
    public boolean existPersonWithId(Integer id) {
        return customers.stream().anyMatch(c->c.getId()==id);
    }

    @Override
    public Optional<Customer> selectAllCustomerById(Integer id) {
        return customers.stream().filter(c -> c.getId() == id)
                .findFirst();
    }

    @Override
    public void insertCostumer(Customer customer) {
        customers.add(customer);
    }


    @Override
    public void deleteCustomer(Integer id) {
         customers.stream().filter(c->c.getId()==id)
                 .findFirst().ifPresent(o->customers.remove(o));
    }

    @Override
    public void updateCustomer(Customer updateCustomer) {
        customers.add(updateCustomer);
    }
}
