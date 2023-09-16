package com.example.demo.herb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HerbService {

    private static final int PAGE_SIZE = 20;
    HerbRepository repository;

    @Autowired
    public HerbService(HerbRepository repository) {
        this.repository = repository;
    }

    public List<HerbEntity> getHerbs(Integer page) {
        return repository.findAll(PageRequest.of(page, PAGE_SIZE)).toList();
    }

    public HerbEntity getHerb(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb don't exist with name: " + name));
    }

    public HerbEntity addHerb(HerbEntity newHerb) {
        return repository.save(newHerb);
    }

    public HerbEntity updateHerb(String name, HerbEntity newHerb) {
        HerbEntity oldHerb = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb don't exist with name: " + name));

        oldHerb.setName(newHerb.getName());
        oldHerb.setSunExposition(newHerb.getSunExposition());
        oldHerb.setWateringFrequency(newHerb.getWateringFrequency());

        return repository.save(oldHerb);
    }

    public void deleteHerb(String name) {
        HerbEntity herb = repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Herb don't exist with name: " + name));
        repository.delete(herb);
    }

}
