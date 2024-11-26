package com.dogWoodFlora.mapper;

import com.dogWoodFlora.dto.AddressDTO;
import com.dogWoodFlora.entity.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    // Entity to DTO
    public AddressDTO toDTO(AddressEntity address) {
        if (address == null) {
            return null;
        }
        return new AddressDTO(
                address.getAddressId(),
                address.getApartmentNo(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPostalCode()
        );
    }

    // DTO to Entity
    public AddressEntity toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        AddressEntity address = new AddressEntity();
        address.setAddressId(addressDTO.getAddressId());
        address.setApartmentNo(addressDTO.getApartmentNo());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        // Customer will be handled separately
        return address;
    }
}
