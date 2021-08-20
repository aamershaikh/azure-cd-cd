package com.sample.microservice.customer.api;

import com.sample.microservice.customer.exceptions.CustomerNotFoundException;
import com.sample.microservice.customer.intercomm.AccountClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sample.microservice.customer.model.Account;
import com.sample.microservice.customer.model.Customer;
import com.sample.microservice.customer.model.CustomerType;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class Api {

    @Autowired
    private AccountClient accountClient;

    private List<Customer> customers;

    public Api() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "12345", "ABC DEF", CustomerType.INDIVIDUAL));
        customers.add(new Customer(2, "12346", "PQR STU", CustomerType.INDIVIDUAL));
        customers.add(new Customer(3, "12347", "UVW XYZ", CustomerType.INDIVIDUAL));
        customers.add(new Customer(4, "12348", "JKL NMP", CustomerType.INDIVIDUAL));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userId/{userId}")
    public Customer findByUserId(@PathVariable("userId") String userId) throws CustomerNotFoundException {
        log.info(String.format("Customer.findByUserId(%s)", userId));
        return customers.stream()
                .filter(it -> it.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("userId : " + userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Customer> findAll() {
        log.info("Customer.findAll()");
        return customers;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Customer findById(@PathVariable("id") Integer id) throws CustomerNotFoundException {
        log.info(String.format("Customer.findById(%s)", id));
        Customer customer = customers.stream()
                .filter(it -> it.getId().intValue() == id.intValue())
                .findFirst().orElseThrow(() -> new CustomerNotFoundException("id : " + id));

        List<Account> accounts = accountClient.getAccounts(id);
        customer.setAccounts(accounts);
        return customer;
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public Customer createNewCustomer(@RequestBody Customer customer) {
        log.info("Customer.createNewCustomer()");
        if (customer.getId() != null) {
            return null;
        }
        int size = customers.size();
        customer.setId(size + 1);
        customers.add(customer);
        return customer;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean deleteCustomer(@PathVariable Integer id) {
        log.info("Customer.deleteCustomer()");
        try {
            Customer byId = findById(id);
            customers.remove(byId);
        } catch (CustomerNotFoundException e) {
            return false;
        }
        return true;
    }

}
