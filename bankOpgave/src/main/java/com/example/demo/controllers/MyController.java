package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import com.example.demo.services.MechanicsService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MyController {

    CustomerService customerService = new CustomerService();
    MechanicsService mechanicsService = new MechanicsService();
    private int activeUserId;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/createAccount")
    public String createAccount() {
        return "createAccount";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/postAccount")
    public String postAccount(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        Customer customer = new Customer(firstName, lastName, password);
        customerService.addCustomerToArrayList(customer);
        return "redirect:/";
    }

    @PostMapping("/postLogin")
    public String postLogin(HttpServletRequest request){
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean loginStatus = customerService.checkLoginInfo(id, password);
        activeUserId = id;
        if (loginStatus){
            return "redirect:/userPage";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/userPage")
    public String userPage(){
        return "userPage";
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("customers", customerService.getListOfCustomers());
        return "test";
    }

    public Customer activeCustomer(){
        for (Customer customer : customerService.getListOfCustomers()){
            if (customer.getId() == activeUserId) {
                return customer;
            }
        }
        return customerService.getListOfCustomers().get(0);
    }

    @GetMapping("/insert")
    public String insert(){
        return "insert";
    }

    @PostMapping("/postInsert")
    public String insert(HttpServletRequest request){
        int insertAmount = Integer.parseInt(request.getParameter("insertAmount"));
        mechanicsService.insertMoney(activeCustomer(), insertAmount);

        return "userPage";
    }

}
