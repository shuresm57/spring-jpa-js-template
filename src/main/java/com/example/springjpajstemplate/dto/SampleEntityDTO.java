package com.example.springjpajstemplate.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class SampleEntityDTO {
    private Long id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
}