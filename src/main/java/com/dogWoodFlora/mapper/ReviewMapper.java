package com.dogWoodFlora.mapper;

import com.dogWoodFlora.dto.ReviewDTO;
import com.dogWoodFlora.entity.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    // Map ReviewEntity to ReviewDTO
    public ReviewDTO toDTO(ReviewEntity reviewEntity) {
        ReviewDTO dto = new ReviewDTO();
        dto.setReviewId(reviewEntity.getReviewId());
        dto.setRating(reviewEntity.getRating());
        dto.setComment(reviewEntity.getComment());
        dto.setReviewDate(reviewEntity.getReviewDate());
        dto.setCustomerId(reviewEntity.getCustomer().getCustomerId());
        dto.setProductId(reviewEntity.getProduct().getProductId());
        return dto;
    }

    // Map ReviewDTO to ReviewEntity
    public ReviewEntity toEntity(ReviewDTO reviewDTO) {
        ReviewEntity entity = new ReviewEntity();
        entity.setReviewId(reviewDTO.getReviewId());
        entity.setRating(reviewDTO.getRating());
        entity.setComment(reviewDTO.getComment());
        entity.setReviewDate(reviewDTO.getReviewDate());
        return entity;
    }
}
