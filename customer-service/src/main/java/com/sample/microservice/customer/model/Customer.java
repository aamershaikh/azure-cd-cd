package com.sample.microservice.customer.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    private Integer id;
    private String pesel;
    private String name;
    private CustomerType type;
    private List<Account> accounts;

    public Customer(int id, String pesel, String name, CustomerType type) {
        this.id = id;
        this.pesel = pesel;
        this.name = name;
        this.type = type;
    }
}
