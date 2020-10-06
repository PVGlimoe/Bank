package com.example.demo.services;

import com.example.demo.models.Customer;

public class MechanicsService {


    public int insertMoney(Customer customer, int amount){
        customer.setBalance(customer.getBalance() + amount);
        return customer.getBalance();
    }

    public int withdrawMoney(Customer customer, int amount){
        customer.setBalance(customer.getBalance() - amount);
        return customer.getBalance();
    }

    public void transferMoney(Customer payingCustomer, Customer receivingCustomer, int amount){
        withdrawMoney(payingCustomer, amount);
        insertMoney(receivingCustomer, amount);
    }


}
