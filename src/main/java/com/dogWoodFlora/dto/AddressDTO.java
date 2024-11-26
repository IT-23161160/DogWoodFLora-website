package com.dogWoodFlora.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddressDTO {
    private Long addressId;
    private String apartmentNo;
    private String street;
    private String city;
    private String state;
    private String postalCode;
}
