package com.dogWoodFlora.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private LocalDateTime addedDate;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @ManyToMany
    private List<ProductEntity> products;
}
