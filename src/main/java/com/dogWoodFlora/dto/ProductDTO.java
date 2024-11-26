package com.dogWoodFlora.dto;

import com.dogWoodFlora.entity.ReviewEntity;
import lombok.*;

import java.sql.Blob;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImage;
    private String productStatus;
    private String category;
    private ReviewEntity review;
}

