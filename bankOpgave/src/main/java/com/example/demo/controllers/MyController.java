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
import java.lang.reflect.Field;
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
    public String createAccount(Model model) throws NoSuchFieldException, IllegalAccessException  {
        int personalId =(int) Customer.class.getField("customerId").get(null) + 1;
        model.addAttribute("personalId", personalId);
        return "createAccount";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/postAccount")
    public String postAccount(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        Customer customer = new Customer(firstName, lastName, password);
        customerService.addCustomerToArrayList(customer);
        return "redirect:/login";
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
    public String userPage(Model model){
        Customer activeUser = customerService.getListOfCustomers().get(activeUserId - 1);
        model.addAttribute("firstName", activeUser.getFirstName());
        model.addAttribute("lastName", activeUser.getLastName());
        model.addAttribute("balance", activeUser.getBalance());
        return "userPage";
    }

    public Customer activeCustomer(){
        for (Customer customer : customerService.getListOfCustomers()){
            if (customer.getId() == activeUserId) {
                return customer;
            }
        }
        return null;
    }

    @GetMapping("/insert")
    public String insert(){
        return "insert";
    }

    @PostMapping("/postInsert")
    public String insert(HttpServletRequest request){
        int insertAmount = Integer.parseInt(request.getParameter("insertAmount"));
        mechanicsService.insertMoney(activeCustomer(), insertAmount);

        return "redirect:/userPage";
    }

    @GetMapping("/withdraw")
    public String withdraw(){
        return "withdraw";
    }

    @PostMapping("/postWithdraw")
    public String postWithdraw(HttpServletRequest request){
        int withdrawAmount = Integer.parseInt(request.getParameter("withdrawAmount"));
        mechanicsService.withdrawMoney(activeCustomer(), withdrawAmount);
        return "redirect:/userPage";
    }

    @GetMapping("/transfer")
    public String transfer(){
        return "transfer";
    }

    public Customer transferReceivingCustomer(int userID){
        for (Customer customer : customerService.getListOfCustomers()){
            if (customer.getId() == userID) {
                return customer;
            }
        }
        return null;

    }
    @PostMapping("/postTransfer")
    public String postTransfer(HttpServletRequest request){
        int transferAmount = Integer.parseInt(request.getParameter("transferAmount"));
        Customer payingCustomer = activeCustomer();
        Customer receivingCustomer = transferReceivingCustomer(Integer.parseInt(request.getParameter("receivingUserId")));
        mechanicsService.transferMoney(payingCustomer, receivingCustomer, transferAmount);
        return "redirect:/userPage";
    }

}
