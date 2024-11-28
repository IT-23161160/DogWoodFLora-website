package com.dogWoodFlora.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private BigDecimal amount;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private PaymentStatus payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Lob
    private byte[] paymentSlip;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity products;

    @OneToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public enum PaymentStatus {
        PENDING, SUCCESSFUL, FAILED
    }

    public enum OrderStatus {
        SUCCEEDED, FAILED, CANCELLED, PENDING
    }

    public String getImageDataBase64() {
        return Base64.getEncoder().encodeToString(this.paymentSlip);
    }

    public void setImageDataFromBase64(String base64Image) {
        if (base64Image != null && !base64Image.isEmpty()) {
            this.paymentSlip = Base64.getDecoder().decode(base64Image);
        } else {
            this.paymentSlip = null; // Set to null if the input string is empty or null
        }
    }
}
