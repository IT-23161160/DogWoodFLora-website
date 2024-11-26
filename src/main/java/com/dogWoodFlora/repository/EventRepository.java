package com.dogWoodFlora.repository;

import com.dogWoodFlora.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <EventEntity, Long> {
}
