package com.sample.microservice.customer.intercomm;

import java.util.List;

import com.sample.microservice.customer.model.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account-service")
public interface AccountClient {

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{customerId}")
    List<Account> getAccounts(@PathVariable("customerId") Integer customerId);
    
}
