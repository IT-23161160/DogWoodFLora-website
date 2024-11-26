package com.dogWoodFlora.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String apartmentNo;
    private String street;
    private String city;
    private String state;
    private String postalCode;

    /*@OneToOne(mappedBy = "address")
    private CustomerEntity customer; */

}
