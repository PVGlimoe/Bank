package com.example.demo.models;

public class Customer {
    private String firstName;
    private String lastName;
    private int id;
    static int customerId = 0;

    public Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        customerId++;
        id = customerId;
    }
}
