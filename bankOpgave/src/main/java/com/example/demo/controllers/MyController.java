package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import com.example.demo.services.MechanicsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {

    CustomerService customerService = new CustomerService();
    MechanicsService mechanicsService = new MechanicsService();


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
        customerService.addCustomer(firstName, lastName, password);
        return "redirect:/login";
    }

    @PostMapping("/postLogin")
    public String postLogin(HttpServletRequest request){
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean loginStatus = customerService.checkLoginInfo(id, password);
        if (loginStatus){
            customerService.setActiveUser(customerService.getCustomerById(id));
            return "redirect:/userPage";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/userPage")
    public String userPage(Model model){
        Customer activeUser = customerService.getActiveUser();
        model.addAttribute("user", activeUser);
        return "userPage";
    }

    public Customer activeCustomer(){
        return customerService.getActiveUser();
    }

    @GetMapping("/insert")
    public String insert(){
        return "insert";
    }

    @PostMapping("/postInsert")
    public String insert(HttpServletRequest request){
        int insertAmount = Integer.parseInt(request.getParameter("insertAmount"));
        mechanicsService.insertMoney(customerService.getActiveUser(), insertAmount);

        return "redirect:/userPage";
    }

    @GetMapping("/withdraw")
    public String withdraw(){
        return "withdraw";
    }

    @PostMapping("/postWithdraw")
    public String postWithdraw(HttpServletRequest request){
        int withdrawAmount = Integer.parseInt(request.getParameter("withdrawAmount"));
        mechanicsService.withdrawMoney(customerService.getActiveUser(), withdrawAmount);
        return "redirect:/userPage";
    }

    @GetMapping("/transfer")
    public String transfer(){
        return "transfer";
    }


    @PostMapping("/postTransfer")
    public String postTransfer(HttpServletRequest request){
        int transferAmount = Integer.parseInt(request.getParameter("transferAmount"));
        Customer payingCustomer = customerService.getActiveUser();
        Customer receivingCustomer = customerService.getCustomerById(Integer.parseInt(request.getParameter("receivingUserId")));
        mechanicsService.transferMoney(payingCustomer, receivingCustomer, transferAmount);
        return "redirect:/userPage";
    }

}
