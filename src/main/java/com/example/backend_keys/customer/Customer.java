package com.example.backend_keys.customer;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.order.Order;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "email"
                ),
        }
)
public class Customer {
        @Id
        //it like auto increment
        @SequenceGenerator(
                name = "customer_id_sequence",
                sequenceName = "customer_id_sequence"
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "customer_id_sequence"
        )
        @Column(name = "id")

        private Integer id;
    @Column(nullable = false)
        private String name;

    @Column(name = "email",nullable = false,
            columnDefinition = "TEXT"
    )
        private String email;
    @Column(
            nullable = false
    )
        private int age;

    @OneToOne(
            mappedBy = "customer",
            cascade = {CascadeType.REMOVE,CascadeType.PERSIST},
            orphanRemoval = true
    )
    private Cart cart;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Customer() {

    }

    public Cart getCart() {
        return cart;
    }

    public Customer(int id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }

    public Customer( String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return id == customer.id && age == customer.age && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, email, age);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    '}';
        }

}
