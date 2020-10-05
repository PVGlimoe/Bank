package com.example.demo.services;

import com.example.demo.models.Customer;

public class MechanicsService {


    public int insertMoney(Customer customer, int amount){
        customer.setBalance(customer.getBalance() + amount);
        return customer.getBalance();
    }


}
