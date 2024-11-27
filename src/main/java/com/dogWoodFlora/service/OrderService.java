package com.dogWoodFlora.service;

import com.dogWoodFlora.dto.OrderDTO;
import com.dogWoodFlora.entity.OrderEntity;
import com.dogWoodFlora.entity.ProductEntity;
import com.dogWoodFlora.entity.UserEntity;
import com.dogWoodFlora.mapper.OrderMapper;
import com.dogWoodFlora.repository.OrderRepository;
import com.dogWoodFlora.repository.ProductRepository;
import com.dogWoodFlora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// OrderService.java
@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public OrderDTO placeOrder(Long userId, Long productId) {
        // Find the user by ID
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // Find the product by ID
        Optional<ProductEntity> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        // Create and save the order
        OrderEntity order = new OrderEntity();
        order.setUser(user.get());
        order.setProducts(product.get()); // Assuming OrderEntity has a field for ProductEntity
        order.setDate(LocalDateTime.now());
        order.setStatus(OrderEntity.OrderStatus.PENDING); // Assuming an enum for order status
        orderRepository.save(order);

        // Map the order entity to a DTO and return
        return OrderMapper.toDTO(order);
    }


    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long orderId) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return OrderMapper.toDTO(order.get());
        }
        throw new RuntimeException("Order not found");
    }

    public OrderDTO updateOrder(Long orderId, String newStatus) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            order.get().setStatus(OrderEntity.OrderStatus.valueOf(newStatus));
            orderRepository.save(order.get());
            return OrderMapper.toDTO(order.get());
        }
        throw new RuntimeException("Order not found");
    }

    public void deleteOrder(Long orderId) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        order.ifPresent(orderRepository::delete);
    }

    public List<OrderDTO> getOrdersByUser(Long userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found!");
        }
        List<OrderEntity> orders = orderRepository.findByUser(userOpt.get());
        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

}

