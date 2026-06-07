package com.ai.service.entity;

import lombok.Data;

@Data
public class TravelPlace {
    private String name;
    private String type;
    private String city;
    private String state;
    private String country;
    private String description;
    private Double latitude;
    private Double longitude;
}
