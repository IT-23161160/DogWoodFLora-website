package com.dogWoodFlora.mapper;

import com.dogWoodFlora.dto.CartDTO;
import com.dogWoodFlora.entity.CartEntity;
import com.dogWoodFlora.entity.ProductEntity;
import com.dogWoodFlora.repository.UserRepository;
import com.dogWoodFlora.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {
    @Autowired
    private UserRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartEntity toEntity(CartDTO cartDTO) {
        CartEntity cart = new CartEntity();
        cart.setCartId(cartDTO.getCartId());
        cart.setAddedDate(cartDTO.getAddedDate());
        cart.setCustomer(customerRepository.findById(cartDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + cartDTO.getCustomerId())));
        cart.setProducts(cartDTO.getProductIds()
                .stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found with id " + productId)))
                .collect(Collectors.toList()));
        return cart;
    }

    public CartDTO toDTO(CartEntity cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setAddedDate(cart.getAddedDate());
        cartDTO.setCustomerId(cart.getCustomer().getCustomerId());
        cartDTO.setProductIds(cart.getProducts()
                .stream()
                .map(ProductEntity::getProductId)
                .collect(Collectors.toList()));
        return cartDTO;
    }
}
