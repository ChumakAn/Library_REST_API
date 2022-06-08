package com.example.library.controller;

import com.example.library.mapper.AbstractMapper;
import com.example.library.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController<T, DTO, ID> {


    protected abstract AbstractService<T, ID,DTO> getService();

    protected abstract AbstractMapper<T, DTO> getMapper();

    @CrossOrigin(origins = "https://java.lviv.education")
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<DTO>> getAll() {
        List<DTO> objectsDto = getService().getAll();
        if(objectsDto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(objectsDto, HttpStatus.OK);

    }

    @CrossOrigin(origins = "https://java.lviv.education")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody
    ResponseEntity<DTO> getById(@PathVariable ID id) {
        DTO object = getService().getById(id);
        if (object == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<>(object, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "https://java.lviv.education")
    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Void> create(@RequestBody T newObject) {
        getService().create(newObject);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "https://java.lviv.education")
    @RequestMapping(method = RequestMethod.PUT,
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<T> update(@PathVariable ID id, @RequestBody T object) {
        if (getService().getById(id) != null) {
            getService().update(id, object);
            return new ResponseEntity<>(getService().update(id, object), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "https://java.lviv.education")
    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) {
        if (getService().getById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            getService().deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}
