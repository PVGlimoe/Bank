package com.example.demo.models;

public class Customer {
    private String firstName;
    private String lastName;
    static int customerId = 1;

    public Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        customerId++;
    }
}
