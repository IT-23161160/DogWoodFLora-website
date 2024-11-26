package com.dogWoodFlora.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Base64;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Long quantityAvailable;
    private String category;

    @Lob
    private byte[] productImage;

    @Enumerated(EnumType.STRING)
    private Status productStatus;

    public enum Status {
        IN_STOCK, OUT_OF_STOCK, COMING_SOON
    }

    public String getImageDataBase64() {
        return Base64.getEncoder().encodeToString(this.productImage);
    }

    public void setImageDataFromBase64(String base64Image) {
        if (base64Image != null && !base64Image.isEmpty()) {
            this.productImage = Base64.getDecoder().decode(base64Image);
        } else {
            this.productImage = null; // Set to null if the input string is empty or null
        }
    }

}
