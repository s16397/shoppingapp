package com.example.mariusz.shoppinglistapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MyLocation {
    private String id;
    private String name;
    private String description;
    private double radius;
    private double longitude;
    private double latitude;
}
