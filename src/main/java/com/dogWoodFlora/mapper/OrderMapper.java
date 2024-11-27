package com.dogWoodFlora.mapper;

import com.dogWoodFlora.dto.OrderDTO;
import com.dogWoodFlora.entity.OrderEntity;

public class OrderMapper {
    public static OrderDTO toDTO(OrderEntity orderEntity) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(orderEntity.getOrderId());
        dto.setOrderDate(orderEntity.getProducts().getProductId().toString());
        dto.setStatus(orderEntity.getStatus().toString());
        dto.setUserId(orderEntity.getUser().getCustomerId());
        dto.setPaymentSlip(orderEntity.getImageDataBase64());
        return dto;
    }
}
