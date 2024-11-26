package com.dogWoodFlora.repository;

import com.dogWoodFlora.entity.OrderEntity;
import com.dogWoodFlora.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUser(UserEntity user);
}
