package com.example.demo.services;

import com.example.demo.models.Customer;

import java.util.ArrayList;

public class CustomerService {

   private ArrayList<Customer> listOfCustomers = new ArrayList<>();

   public void addCustomerToArrayList(Customer customer){
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
}
