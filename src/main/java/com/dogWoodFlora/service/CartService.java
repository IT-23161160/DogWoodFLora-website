package com.dogWoodFlora.service;

import com.dogWoodFlora.dto.CartDTO;
import com.dogWoodFlora.entity.CartEntity;
import com.dogWoodFlora.mapper.CartMapper;
import com.dogWoodFlora.repository.CartRepository;
import com.dogWoodFlora.repository.UserRepository;
import com.dogWoodFlora.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartMapper cartMapper;


    public CartDTO addCart(CartEntity cart) {
        CartEntity addedCart = cartRepository.save(cart);
        return cartMapper.toDTO(addedCart);
    }


    public CartDTO getCartById(Long id) {
        CartEntity cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));
        return cartMapper.toDTO(cart);
    }


    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toDTO)
                .collect(Collectors.toList());
    }


    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        CartEntity existingCart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));

        existingCart.setAddedDate(cartDTO.getAddedDate());
        existingCart.setCustomer(customerRepository.findById(cartDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + cartDTO.getCustomerId())));
        existingCart.setProducts(cartDTO.getProductIds()
                .stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found with id " + productId)))
                .collect(Collectors.toList()));

        CartEntity updatedCart = cartRepository.save(existingCart);
        return cartMapper.toDTO(updatedCart);
    }


    public void deleteCart(Long id) {
        CartEntity cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + id));
        cartRepository.delete(cart);
    }

}

