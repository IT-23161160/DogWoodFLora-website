package com.dogWoodFlora.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewDTO {
    private Long reviewId;
    private Integer rating;
    private String comment;
    private LocalDateTime reviewDate;
    private Long customerId;
    private Long productId;

}
