package com.dogWoodFlora.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartDTO {
    private Long cartId;
    private LocalDateTime addedDate;
    private Long customerId;
    private List<Long> productIds;
}