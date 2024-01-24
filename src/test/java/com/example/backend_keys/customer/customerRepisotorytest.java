package com.example.backend_keys.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class customerRepisotorytest {

    @Autowired
    private CustomerRepisotory underTest;

    @Test
    void itShouldSelectCustomerPhoneNumber() {
        //Given
        //when
        //then

    }
    @Test
    void itShouldSaveCustomer() {
        // Given
        //UUID id = UUID.randomUUID();
        int id=1;
        Customer customer=new Customer(1,"Alex","alex@gmail.com",21);


        // When
        underTest.save(customer);
        // Then
        Optional<Customer> optionalCustomer = underTest.findById(id);
        assertThat(optionalCustomer)
                .isPresent()
                .hasValueSatisfying(c -> {
                   /* assertThat(c.getId()).isEqualTo(id);
                    assertThat(c.getName()).isEqualTo("Abel");
                  assertThat(c.getPhoneNumber()).isEqualTo("0000");*/
                    assertThat(c).isEqualToComparingFieldByField(customer);
                    //assertThat(c).isEqualToComparingFieldByField(customer);
                });
    }
}
