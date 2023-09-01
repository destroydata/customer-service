package com.example.customer.service;

import com.example.customer.config.TokenInfo;
import com.example.customer.domain.entity.Customer;
import com.example.customer.domain.request.CustomerRequest;
import com.example.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public void save(CustomerRequest request){
        customerRepository.save(request.toEntity());
    }
    public void checkSignup(TokenInfo tokenInfo){
        UUID id = tokenInfo.getId();
        Optional<Customer> byId = customerRepository.findById(id);
        if(byId.isEmpty()){
            CustomerRequest customerRequest =
                    new CustomerRequest(id, tokenInfo.getName(), tokenInfo.getNumber());
            save(customerRequest);
        }
    }
}
