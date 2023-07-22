package com.example.demo.herb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HerbService {

    HerbRepository repository;

    @Autowired
    public HerbService(HerbRepository repository) {
        this.repository = repository;
    }

    public List<HerbEntity> getHebrs() {
        return repository.findAll();
    }

//    public HerbEntity getHerb(long id) {
//        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with id: " + id));
//    }

    public HerbEntity getHerb(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with name: " + name));
    }

    public void addHerb(HerbEntity newHerb) {
        repository.save(newHerb);
    }

    public void updateHerb(long id, HerbEntity newHerb) {
        HerbEntity updateHerb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with id: " + id));

        updateHerb.setName(newHerb.getName());
        updateHerb.setSunExposition(newHerb.getSunExposition());
        updateHerb.setWateringFrequency(newHerb.getWateringFrequency());

        repository.save(updateHerb);
    }

    public void deleteHerb(long id) {
        HerbEntity herb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with id: " + id));
        repository.delete(herb);
    }
    public void deleteHerb(String name) {
        HerbEntity herb = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with name: " + name));
        repository.delete(herb);
    }

}
