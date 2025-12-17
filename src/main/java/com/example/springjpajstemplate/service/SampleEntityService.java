package com.example.springjpajstemplate.service;

import com.example.springjpajstemplate.model.SampleEntity;
import com.example.springjpajstemplate.repository.SampleEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class SampleEntityService extends CrudService<SampleEntity, Long> {
    
    public SampleEntityService(SampleEntityRepository repository) {
        super(repository);
    }
}