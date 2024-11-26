package com.dogWoodFlora.controller;

import com.dogWoodFlora.dto.CartDTO;
import com.dogWoodFlora.entity.CartEntity;
import com.dogWoodFlora.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public CartDTO addCart(@RequestBody CartEntity cart) {
        return cartService.addCart(cart);
    }

    @GetMapping("/{id}")
    public CartDTO getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @GetMapping
    public List<CartDTO> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PutMapping("/{id}")
    public CartDTO updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        return cartService.updateCart(id, cartDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}

