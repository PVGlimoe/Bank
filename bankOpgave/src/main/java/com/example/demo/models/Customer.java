package com.example.demo.models;

import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private int id;
    private String password;
    static int customerId = 0;


    public Customer(String firstName, String lastName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        customerId++;
        id = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getPassword(){
        return password;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}
