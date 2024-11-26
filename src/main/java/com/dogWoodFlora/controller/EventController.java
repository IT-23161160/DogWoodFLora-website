package com.dogWoodFlora.controller;

import com.dogWoodFlora.dto.EventDTO;
import com.dogWoodFlora.dto.ProductDTO;
import com.dogWoodFlora.dto.ReviewDTO;
import com.dogWoodFlora.entity.EventEntity;
import com.dogWoodFlora.service.EventService;
import com.dogWoodFlora.service.ProductService;
import com.dogWoodFlora.service.ReviewService;
import com.sun.java.accessibility.util.EventID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    ReviewService reviewService;
    @Autowired
    private ProductService productService;

    @GetMapping("/event")
    public String getAllEvents(Model model) {
        List<EventDTO> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        model.addAttribute("event", new EventEntity());
        return "event";  // Corrected to "event.html"
    }


    @GetMapping("/users/event")
    public String getEvents(Model model, @RequestParam(name = "eventCategory", defaultValue = "All Photos") String eventCategory) {

        List<EventDTO> events;
        if ("All Photos".equals(eventCategory)) {
            events = eventService.getAllEvents(); // Show all if "All Photos" selected
        } else {
            events = eventService.getByCategory(eventCategory);  // Filter by category
        }
        model.addAttribute("selectedCategory", eventCategory);
        model.addAttribute("events", events);
        return "cEvent"; // template for displaying events
    }

    @GetMapping("/users/product")
    public String getProducts(Model model) {
        List<ProductDTO> products = productService.getAllProducts(); // Fetch all events from DB
        model.addAttribute("products", products); // Add events to the model
        return "cProduct"; // Return the view name, where the events will be displayed
    }



    @GetMapping("/event/{id}")
    public String getEventById(@PathVariable Long id, Model model) {
        EventDTO event = eventService.getEventById(id);
        if (event != null) {
            model.addAttribute("event", event);
            return "event";  // Corrected to "event.html"
        } else {
            return "redirect:/event";
        }
    }

    @GetMapping("/event/search/{searchText}")
    public String getEventsBySearchText(@PathVariable String searchText, Model model) {
        List<EventDTO> result = eventService.searchEvents(searchText);
        model.addAttribute("events", result);
        return "event";  // Corrected to "event.html"
    }

    @GetMapping("event/categories")
    public String getAllCategories(Model model) {
        Set<String> categories = eventService.allCategories();
        model.addAttribute("categories", categories);
        return "cEvent";  // Assuming the categories template is correct
    }

    @GetMapping("/event/categories/{category}")
    public String getEventsByCategory(@PathVariable String category, Model model) {
        List<EventDTO> events = eventService.getByCategory(category);
        model.addAttribute("event", events);
        model.addAttribute("selectedCategory", category);
        return "event";  // Corrected to "event.html"
    }



    @PostMapping("/event")
    public String saveEvent(@ModelAttribute("event") EventEntity event,
                            @RequestParam("eventImageFile") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            event.setImageDataFromBase64(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        eventService.saveEvent(event);
        return "redirect:/event";
    }

    @GetMapping("/event/{id}/edit")
    public String showEditEventForm(@PathVariable Long id, Model model) {
        EventDTO event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "eventshow";  // Corrected to "event.html"
    }



    @PostMapping("/event/{id}")
    public String updateEvent(@PathVariable Long id,
                              @ModelAttribute("event") EventEntity event,
                              @RequestParam("eventImageFile") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            event.setImageDataFromBase64(Base64.getEncoder().encodeToString(file.getBytes()));
        }
        event.setEventId(id);
        eventService.saveEvent(event);
        return "redirect:/event";
    }

    @PostMapping("/event/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/event";
    }




}