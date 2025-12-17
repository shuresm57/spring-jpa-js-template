package com.example.springjpajstemplate.controller;

import com.example.springjpajstemplate.dto.SampleEntityDTO;
import com.example.springjpajstemplate.model.SampleEntity;
import com.example.springjpajstemplate.service.SampleEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sample-entities")
public class SampleEntityController {

    private final SampleEntityService sampleEntityService;

    public SampleEntityController(SampleEntityService sampleEntityService) {
        this.sampleEntityService = sampleEntityService;
    }

    @GetMapping
    public ResponseEntity<List<SampleEntityDTO>> getAllEntities() {
        List<SampleEntity> entities = sampleEntityService.findAll();
        List<SampleEntityDTO> dtos = entities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleEntityDTO> getEntityById(@PathVariable Long id) {
        SampleEntity entity = sampleEntityService.findById(id);
        return ResponseEntity.ok(convertToDTO(entity));
    }

    @PostMapping
    public ResponseEntity<SampleEntityDTO> createEntity(@RequestBody SampleEntityDTO dto) {
        SampleEntity entity = convertToEntity(dto);
        SampleEntity savedEntity = sampleEntityService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleEntityDTO> updateEntity(@PathVariable Long id, @RequestBody SampleEntityDTO dto) {
        sampleEntityService.findById(id); // Check if exists
        SampleEntity entity = convertToEntity(dto);
        entity.setId(id);
        SampleEntity updatedEntity = sampleEntityService.save(entity);
        return ResponseEntity.ok(convertToDTO(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        sampleEntityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SampleEntityDTO convertToDTO(SampleEntity entity) {
        SampleEntityDTO dto = new SampleEntityDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    private SampleEntity convertToEntity(SampleEntityDTO dto) {
        SampleEntity entity = new SampleEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}