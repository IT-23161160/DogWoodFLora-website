package com.dogWoodFlora.service;

import com.dogWoodFlora.dto.UserDTO;
import com.dogWoodFlora.entity.UserEntity;
import com.dogWoodFlora.mapper.UserMapper;
import com.dogWoodFlora.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper customerMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Retrieve all customers
    public List<UserDTO> getAllCustomers() {
        return userRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a customer by name
    public List<UserDTO> searchCustomers(String searchText) {
        List<UserDTO> firstName = userRepository.findAll()
                .stream()
                .filter(customer -> customer.getFirstName().toLowerCase()
                        .contains(searchText.toLowerCase()))
                .map(customerMapper::toDTO)
                .toList();
        List<UserDTO> lastName = userRepository.findAll()
                .stream()
                .filter(customer->customer.getLastName().toLowerCase()
                        .contains(searchText.toLowerCase()))
                .map(customerMapper::toDTO)
                .toList();
        List<UserDTO> result = new ArrayList<>();
        result.addAll(firstName);
        result.addAll(lastName);
        return result;
    }

    //Retrieve a user by Email
    public UserEntity getByUserName(String userName) {
        List<UserEntity> users = userRepository.findAll();
        return users
                .stream()
                .filter(u->u.getEmail().equals(userName))
                .findFirst()
                .get();
    }

    // Retrieve a customer by ID
    public UserEntity getCustomerById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Create customer
    public UserEntity registerUser(UserEntity user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        Optional<UserEntity> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            UserEntity user = existingUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        userRepository.deleteById(id);
    }
}
