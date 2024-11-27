package com.dogWoodFlora.controller;

import com.dogWoodFlora.dto.OrderDTO;
import com.dogWoodFlora.security.CustomUserDetails;
import com.dogWoodFlora.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String viewOrders(Model model) {
        List<OrderDTO> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "list";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam Long productId, Model model) {
        // Get the currently authenticated user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId(); // Assuming your CustomUserDetails class has a getId() method.

        // Place the order using the user ID and product ID
        OrderDTO order = orderService.placeOrder(userId, productId);
        model.addAttribute("order", order);
        return "confirmation";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{orderId}")
    public String viewOrder(@PathVariable Long orderId, Model model) {
        OrderDTO order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);
        return "view";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update/{orderId}")
    public String updateOrder(@PathVariable Long orderId, @RequestParam String status, Model model) {
        OrderDTO order = orderService.updateOrder(orderId, status);
        model.addAttribute("order", order);
        return "view";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/orders";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myOrders/{userId}")
    public String viewUserOrders(@PathVariable Long userId, Model model) {
        List<OrderDTO> userOrders = orderService.getOrdersByUser(userId);
        model.addAttribute("orders", userOrders);
        return "userOrders"; // Points to a new Thymeleaf template
    }
}
