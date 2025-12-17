package com.example.springjpajstemplate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "sample_entities")
@Data
@EqualsAndHashCode(callSuper = true)
public class SampleEntity extends BaseEntity {
    
    private String name;
    private String description;
}