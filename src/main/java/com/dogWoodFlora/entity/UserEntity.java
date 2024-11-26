package com.dogWoodFlora.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne(mappedBy = "customer")
    private CartEntity cart;

    @OneToOne(mappedBy = "user")
    private OrderEntity order;

    @OneToMany(mappedBy = "customer")
    private Set<ReviewEntity> reviews;

}
