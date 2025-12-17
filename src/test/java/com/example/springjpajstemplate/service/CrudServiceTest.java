package com.example.springjpajstemplate.service;

import com.example.springjpajstemplate.exception.ResourceNotFoundException;
import com.example.springjpajstemplate.model.SampleEntity;
import com.example.springjpajstemplate.repository.SampleEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudServiceTest {

    @Mock
    private SampleEntityRepository mockRepository;

    private SampleEntityService service;
    private SampleEntity testEntity;

    @BeforeEach
    void setUp() {
        service = new SampleEntityService(mockRepository);
        testEntity = new SampleEntity();
        testEntity.setId(1L);
        testEntity.setName("Test Entity");
        testEntity.setDescription("Test Description");
    }

    @Test
    void findAll_ShouldReturnAllEntities() {
        // Given
        SampleEntity entity1 = new SampleEntity();
        entity1.setId(1L);
        entity1.setName("Entity 1");
        
        SampleEntity entity2 = new SampleEntity();
        entity2.setId(2L);
        entity2.setName("Entity 2");
        
        List<SampleEntity> entities = Arrays.asList(entity1, entity2);
        when(mockRepository.findAll()).thenReturn(entities);

        // When
        List<SampleEntity> result = service.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("Entity 1", result.get(0).getName());
        assertEquals("Entity 2", result.get(1).getName());
        verify(mockRepository).findAll();
    }

    @Test
    void save_ShouldSaveAndReturnEntity() {
        // Given
        when(mockRepository.save(any(SampleEntity.class))).thenReturn(testEntity);

        // When
        SampleEntity result = service.save(testEntity);

        // Then
        assertEquals(testEntity.getId(), result.getId());
        assertEquals(testEntity.getName(), result.getName());
        assertEquals(testEntity.getDescription(), result.getDescription());
        verify(mockRepository).save(testEntity);
    }

    @Test
    void findById_WhenEntityExists_ShouldReturnEntity() {
        // Given
        when(mockRepository.findById(1L)).thenReturn(Optional.of(testEntity));

        // When
        Optional<SampleEntity> result = Optional.ofNullable(service.findById(1L));

        // Then
        assertTrue(result.isPresent());
        assertEquals(testEntity.getId(), result.get().getId());
        assertEquals(testEntity.getName(), result.get().getName());
        verify(mockRepository).findById(1L);
    }

    @Test
    void findByIdOrThrow_WhenEntityExists_ShouldReturnEntity() {
        // Given
        when(mockRepository.findById(1L)).thenReturn(Optional.of(testEntity));

        // When
        SampleEntity result = service.findById(1L);

        // Then
        assertEquals(testEntity.getId(), result.getId());
        assertEquals(testEntity.getName(), result.getName());
        verify(mockRepository).findById(1L);
    }

    @Test
    void findByIdOrThrow_WhenEntityDoesNotExist_ShouldThrowException() {
        // Given
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.findById(1L)
        );
        
        assertEquals("Entity not found with id: 1", exception.getMessage());
        verify(mockRepository).findById(1L);
    }

    @Test
    void deleteById_WhenEntityExists_ShouldDeleteEntity() {
        // Given
        when(mockRepository.existsById(1L)).thenReturn(true);

        // When
        service.deleteById(1L);

        // Then
        verify(mockRepository).existsById(1L);
        verify(mockRepository).deleteById(1L);
    }

    @Test
    void deleteById_WhenEntityDoesNotExist_ShouldThrowException() {
        // Given
        when(mockRepository.existsById(1L)).thenReturn(false);

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> service.deleteById(1L)
        );
        
        assertEquals("Entity not found with id: 1", exception.getMessage());
        verify(mockRepository).existsById(1L);
        verify(mockRepository, never()).deleteById(1L);
    }
}