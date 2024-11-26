package com.dogWoodFlora.service;

import com.dogWoodFlora.dto.AddressDTO;
import com.dogWoodFlora.entity.AddressEntity;
import com.dogWoodFlora.mapper.AddressMapper;
import com.dogWoodFlora.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    // Retrieve all addresses
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Retrieve an address by ID
    public AddressDTO getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::toDTO)
                .orElse(null);
    }

    // Create or update address
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        AddressEntity addressEntity = addressMapper.toEntity(addressDTO);
        AddressEntity savedAddress = addressRepository.save(addressEntity);
        return addressMapper.toDTO(savedAddress);
    }

    // Delete an address by ID
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
