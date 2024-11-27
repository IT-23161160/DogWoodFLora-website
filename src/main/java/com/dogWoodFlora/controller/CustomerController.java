package com.dogWoodFlora.controller;

import com.dogWoodFlora.dto.UserDTO;
import com.dogWoodFlora.entity.UserEntity;
import com.dogWoodFlora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getAllCustomers(Model model) {
        List<UserDTO> customers = userService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers.html";
    }

    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentUser(authentication, #id)")
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        UserEntity customer = userService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer-details";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search/{searchText}")
    public String getCustomerBySearchText(@RequestParam String searchText, Model model) {
        List<UserDTO> customers = userService.searchCustomers(searchText);
        model.addAttribute("customers", customers);
        model.addAttribute("searchText", searchText);
        return "users/customers";
    }

    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentUser(authentication, #id)")
    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        UserEntity customer = userService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "users/edit-customer";
    }

    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentUser(authentication, #id)")
    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute("customer") UserEntity customer) {
        customer.setCustomerId(id);
        userService.updateUser(id, customer);
        return "redirect:/users";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
        return "redirect:/users";
    }
}
