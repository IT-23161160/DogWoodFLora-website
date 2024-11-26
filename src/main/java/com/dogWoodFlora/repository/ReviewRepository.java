package com.dogWoodFlora.repository;

import com.dogWoodFlora.entity.ProductEntity;
import com.dogWoodFlora.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByProduct(ProductEntity product);
}
