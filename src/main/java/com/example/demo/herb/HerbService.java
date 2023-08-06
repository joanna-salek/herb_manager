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

    public List<HerbEntity> getHerbs() {
        return repository.findAll();
    }

    public HerbEntity getHerb(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with name: " + name));
    }

    public void addHerb(HerbEntity newHerb) {
        repository.save(newHerb);
    }

    public void updateHerb(String name, HerbEntity updateHerb) {
        HerbEntity newHerb = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with name: " + name));

        newHerb.setName(updateHerb.getName());
        newHerb.setSunExposition(updateHerb.getSunExposition());
        newHerb.setWateringFrequency(updateHerb.getWateringFrequency());

        repository.save(newHerb);
    }

    public void deleteHerb(String name) {
        HerbEntity herb = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb not exist with name: " + name));
        repository.delete(herb);
    }

}
