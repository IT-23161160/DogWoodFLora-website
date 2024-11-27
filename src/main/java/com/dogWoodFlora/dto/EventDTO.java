package com.dogWoodFlora.dto;


import lombok.*;

import java.sql.Blob;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String eventImage;
    private String eventCategory;

}

