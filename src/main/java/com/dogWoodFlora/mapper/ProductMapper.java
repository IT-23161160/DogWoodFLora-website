package com.dogWoodFlora.mapper;

import com.dogWoodFlora.dto.ProductDTO;
import com.dogWoodFlora.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;

@Component
public class ProductMapper {

    //Entity to Dto
    public ProductDTO toDTO(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setProductId(productEntity.getProductId());
        dto.setProductName(productEntity.getProductName());
        dto.setProductDescription(productEntity.getProductDescription());
        dto.setProductPrice(productEntity.getProductPrice().doubleValue());
        dto.setProductImage(productEntity.getImageDataBase64());
        dto.setProductStatus(String.valueOf(productEntity.getProductStatus()));
        dto.setCategory(productEntity.getCategory());
        return dto;
    }

    //Dto to Entity
    public ProductEntity toEntity(ProductDTO productDTO) throws SQLException {
        if (productDTO == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setProductId(productDTO.getProductId());
        entity.setProductName(productDTO.getProductName());
        entity.setProductDescription(productDTO.getProductDescription());
        entity.setProductPrice(BigDecimal.valueOf(productDTO.getProductPrice()));
        return entity;
    }

}

