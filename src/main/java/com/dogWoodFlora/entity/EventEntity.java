package com.dogWoodFlora.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.Base64;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "event")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String eventCategory;

    @Lob
    private byte[] eventImage;

    public String getImageDataBase64() {
        return Base64.getEncoder().encodeToString(this.eventImage);
    }

    public void setImageDataFromBase64(String base64Image) {
        if (base64Image != null && !base64Image.isEmpty()) {
            this.eventImage = Base64.getDecoder().decode(base64Image);
        } else {
            this.eventImage = null; // Set to null if the input string is empty or null
        }
    }


    public String getCategory() {
        return this.eventCategory;
    }

}

