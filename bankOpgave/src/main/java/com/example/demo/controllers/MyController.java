package com.example.demo.controllers;

import com.example.demo.models.Customer;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/createAccount")
    public String createAccount(){
        return "createAccount";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/postAccount")
    public String postAccount(HttpServletRequest request){
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Customer customer = new Customer(firstName, lastName);
        return "redirect:/";
    }
}
