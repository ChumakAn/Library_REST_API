package com.example.library.service;

import com.example.library.mapper.AbstractMapper;
import javassist.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractService<T, ID,DTO> {
    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract AbstractMapper<T,DTO> getMapper();

    public List<DTO> getAll() {
        return getRepository().findAll().stream().map(getMapper()::mapObjectToDto).collect(Collectors.toList());
    }

    public DTO getById(ID id) {
        try {
            return getMapper().mapObjectToDto(getRepository().getById(id));
        }
        catch (EntityNotFoundException e){
            return null;
        }
    }

    public T create(T object) {
        return getRepository().save(object);
    }

    public T update(ID id, T object) {
        if (getRepository().findById(id).isPresent()) {
            return getRepository().save(object);
        } else {
            return null;
        }
    }

    public void deleteById(ID id) {
        if (getRepository().findById(id).isPresent()) {
            getRepository().deleteById(id);
        }
    }

}