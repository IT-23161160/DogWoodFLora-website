package com.dogWoodFlora.service;

import com.dogWoodFlora.dto.EventDTO;
import com.dogWoodFlora.entity.EventEntity;
import com.dogWoodFlora.mapper.EventMapper;
import com.dogWoodFlora.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    public EventEntity saveEvent(EventEntity eventEntity) {
        return eventRepository.save(eventEntity);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::toDTO)
                .orElse(null);
    }

    public List<EventDTO> searchEvents(String text) {
        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getEventName().toLowerCase()
                        .contains(text.toLowerCase()))
                .map(eventMapper::toDTO)
                .toList();
    }

    public List<EventDTO> getByCategory(String category) {
        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getCategory().toLowerCase()
                        .contains(category.toLowerCase()))
                .map(eventMapper::toDTO)
                .toList();
    }

    public Set<String> allCategories() {
        return eventRepository.findAll()
                .stream()
                .map(EventEntity::getCategory)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}

