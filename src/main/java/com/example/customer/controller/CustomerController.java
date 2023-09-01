package com.example.customer.controller;

import com.example.customer.config.JwtService;
import com.example.customer.config.TokenInfo;
import com.example.customer.domain.entity.Customer;
import com.example.customer.domain.request.CustomerRequest;
import com.example.customer.domain.request.SignupRequest;
import com.example.customer.domain.response.SignupResponse;
import com.example.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService service;
    private final JwtService jwtService;
    @PostMapping("check")
    public SignupResponse check(
            @RequestHeader("Authorization") String token
    ){
        TokenInfo tokenInfo = jwtService.parseToken(
                token.replace("Bearer ", ""));
        return service.checkSignup(tokenInfo);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CustomerRequest request){
        service.save(request);
    }
    @GetMapping("me")
    public Customer getMe(@RequestHeader("Authorization") String token){
        TokenInfo tokenInfo = jwtService.parseToken(
                token.replace("Bearer ", ""));
        return service.getMe(tokenInfo);
    }
    @PostMapping("signup")
    public SignupResponse signup(
            @RequestBody SignupRequest request,
            @RequestHeader("Authorization") String token){
        TokenInfo tokenInfo = jwtService.parseToken(
                token.replace("Bearer ", ""));
        return service.signup(request, tokenInfo);
    }

    @GetMapping("{id}")
    public Customer getById(@PathVariable String id){
        return service.getById(UUID.fromString(id));
    }


}
