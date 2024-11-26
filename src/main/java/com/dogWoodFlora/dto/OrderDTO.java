package com.dogWoodFlora.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDTO {
    private Long orderId;
    private String orderDate;
    private String status;
    private Long userId;

    // getters and setters
}
