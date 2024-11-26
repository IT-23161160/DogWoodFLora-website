package com.dogWoodFlora.dto;

import com.dogWoodFlora.entity.AddressEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private AddressEntity address;
}
