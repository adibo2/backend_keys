package com.example.backend_keys.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
//sprin alreaty take of this class it create new instance it named beans ths controller is
// beans and we can acces through our app
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    // in bottom CustomerService customerService come from appContext an we can assign it
    //to instance field in the top
    public CustomerController(CustomerService customerService){

        this.customerService=customerService;
    }

    //@RequestMapping(value = "api/v1/customer",method = RequestMethod.GET)
    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable("id") Integer customerId ){
        return customerService.getCustomerById(customerId);

    }



    //get the requestbody this will have json object and maped it to Customerregistrartion
    //and hence maped json to java object
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request){
        customerService.addCustomer(request);
    }
    @DeleteMapping("{id}")
    public void removeCustomer(@PathVariable("id") Integer id){
        customerService.deleteCustomerById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer customerId,
                               @RequestBody CustomerUpdateRequest customerUpdateRequest  ){
        customerService.updateCustomer(customerId,customerUpdateRequest);

    }
}
