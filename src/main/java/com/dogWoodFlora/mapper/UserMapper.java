package com.dogWoodFlora.mapper;

import com.dogWoodFlora.dto.UserDTO;
import com.dogWoodFlora.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //Entity to DTO
    public UserDTO toDTO(UserEntity customer) {
        if (customer == null) {
            return null;
        }
        return new UserDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress() != null ? customer.getAddress() : null
        );
    }

    //DTO to Entity
    public UserEntity toEntity(UserDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        UserEntity customer = new UserEntity();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }
}
