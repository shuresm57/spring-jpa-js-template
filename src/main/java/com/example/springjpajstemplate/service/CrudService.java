package com.example.springjpajstemplate.service;

import com.example.springjpajstemplate.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected CrudService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
    }
    
    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity not found with id: " + id);
        }
        repository.deleteById(id);
    }
}