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
    private String userId;
    private String name;
    private CustomerType type;
    private List<Account> accounts;

    public Customer(int id, String userId, String name, CustomerType type) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
    }
}
