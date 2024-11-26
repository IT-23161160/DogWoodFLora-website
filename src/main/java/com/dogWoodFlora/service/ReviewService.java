package com.dogWoodFlora.service;

import com.dogWoodFlora.dto.ReviewDTO;
import com.dogWoodFlora.entity.UserEntity;
import com.dogWoodFlora.entity.ProductEntity;
import com.dogWoodFlora.entity.ReviewEntity;
import com.dogWoodFlora.mapper.ReviewMapper;
import com.dogWoodFlora.repository.UserRepository;
import com.dogWoodFlora.repository.ProductRepository;
import com.dogWoodFlora.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserRepository customerRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository,
                             UserRepository customerRepository,
                             ProductRepository productRepository,
                             ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.reviewMapper = reviewMapper;
    }

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        UserEntity customer = customerRepository.findById(reviewDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        ProductEntity product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDTO);
        reviewEntity.setCustomer(customer);
        reviewEntity.setProduct(product);

        ReviewEntity savedReview = reviewRepository.save(reviewEntity);

        return reviewMapper.toDTO(savedReview);
    }

    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ReviewEntity> reviews = reviewRepository.findByProduct(product);

        return reviews.stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO getReviewById(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return reviewMapper.toDTO(review);
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setReviewDate(reviewDTO.getReviewDate());

        ReviewEntity updatedReview = reviewRepository.save(review);

        return reviewMapper.toDTO(updatedReview);
    }

    public void deleteReview(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        reviewRepository.delete(review);
    }
}
