package com.example.demo.services;

import com.example.demo.models.Customer;

import java.util.ArrayList;

public class CustomerService {

   private ArrayList<Customer> listOfCustomers = new ArrayList<>();
   private Customer activeUser = null;

   public void addCustomer(String firstName, String lastName, String password){
        Customer customer = new Customer(firstName, lastName, password);
        listOfCustomers.add(customer);
   }

    public ArrayList<Customer> getListOfCustomers() {
        return listOfCustomers;
    }
    
    public boolean checkLoginInfo(int id, String password){
        for (Customer customer : listOfCustomers) {
            if (customer.getId() == id && customer.getPassword().equals(password)){
                return true;
            }
            
        }
       return false;
    }

    public Customer getCustomerById(int id) {
        return listOfCustomers.stream()
                    .filter(customer-> customer.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

    public void setActiveUser(Customer activeUser){
        this.activeUser = activeUser;
    }

    public Customer getActiveUser(){
        return activeUser;
    }
}
