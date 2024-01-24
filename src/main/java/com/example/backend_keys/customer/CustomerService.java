package com.example.backend_keys.customer;


import com.example.backend_keys.exception.CustomerIdNotFound;
import com.example.backend_keys.exception.RequestvalidationException;

import com.example.backend_keys.exception.DuplicateRessource;
import com.example.backend_keys.exception.RessourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//busines class  is service
public class CustomerService {
    private final CustomerDao customerDao;
    //we did qualifier because we have 2 files listdataaccesservice and jpaaccess
    //both are bean an we named it and we use qualifier to let customer use jpa repisotory

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao){
        this.customerDao=customerDao;
    }
    public List<Customer> getAllCustomer(){
        return customerDao.selectAllCustomers();
    }
    public Customer getCustomerById(Integer id){
        return customerDao.selectAllCustomerById(id)
                .orElseThrow(()->new RessourceNotFound(
                        ("customer with id %s".formatted(id))));

    }
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        //check if email exist
        String email= customerRegistrationRequest.email();
        if(customerDao.existPersonwithEmail(email)){
            throw new DuplicateRessource("customer with email %s already exist".formatted(email));
        }
        //add
        customerDao.insertCostumer(new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        ));
    }
    public void deleteCustomerById(Integer id){
        if (!customerDao.existPersonWithId(id)){
            throw new CustomerIdNotFound("customer with id %s not found".formatted(id));
        }

        customerDao.deleteCustomer(id);
    }

    public void updateCustomer(Integer customerId,CustomerUpdateRequest customerUpdateRequest){
        Customer customer= getCustomerById(customerId);

        boolean changes=false;

        if(customerUpdateRequest.name() !=null && !customerUpdateRequest.name().equals(customer.getName())){
            customer.setName(customerUpdateRequest.name());
            changes=true;
        }
        if(customerUpdateRequest.age() !=null && !customerUpdateRequest.age().equals(customer.getAge())){
            customer.setAge(customerUpdateRequest.age());
            changes=true;
        }

        if(customerUpdateRequest.email() !=null && !customerUpdateRequest.email().equals(customer.getEmail())){
            if(customerDao.existPersonwithEmail(customerUpdateRequest.email())){
                throw new DuplicateRessource("email already exist");
            }
            customer.setEmail(customerUpdateRequest.email());
            changes=true;
        }
        if(!changes){
            throw new RequestvalidationException("No data found");
        }
        customerDao.updateCustomer(customer);


    }

}
