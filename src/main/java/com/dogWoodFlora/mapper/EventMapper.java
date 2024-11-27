package com.dogWoodFlora.mapper;

import com.dogWoodFlora.dto.EventDTO;
import com.dogWoodFlora.entity.EventEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;

@Component
public class EventMapper {

    //Entity to Dto
    public EventDTO toDTO(EventEntity eventEntity) {
        if (eventEntity == null) {
            return null;
        }
        EventDTO dto = new EventDTO();
        dto.setEventId(eventEntity.getEventId());
        dto.setEventName(eventEntity.getEventName());
        dto.setEventDescription(eventEntity.getEventDescription());

        dto.setEventImage(eventEntity.getImageDataBase64());

        //dto.setEventCategory(eventEntity.getEventCategory());
        return dto;
    }

    //Dto to Entity
    public EventEntity toEntity(EventDTO eventDTO) throws SQLException {
        if (eventDTO == null) {
            return null;
        }
        EventEntity entity = new EventEntity();
        entity.setEventId(eventDTO.getEventId());
        entity.setEventName(eventDTO.getEventName());
        entity.setEventDescription(eventDTO.getEventDescription());
        entity.setEventCategory(eventDTO.getEventCategory());
        return entity;
    }

}

