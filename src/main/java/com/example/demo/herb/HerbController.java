package com.example.demo.herb;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class HerbController {

    private final HerbService service;

    public HerbController(HerbService service) {
        this.service = service;
    }

    @GetMapping("/herbs")
    public List<HerbEntity> getHerbs() {
        return service.getHebrs();
    }

//    @GetMapping("/herbs/{id}")
//    public HerbEntity getHerbById(@PathVariable long id) {
//        return service.getHerb(id);
//    }

    @GetMapping("/herbs/{name}")
    public HerbEntity getHerbByName(@PathVariable String name) {
        return service.getHerb(name);
    }

    @PostMapping("/herbs")
    public void postHerb(@RequestBody HerbEntity newHerb) {
        service.addHerb(newHerb);
    }

    @PutMapping("/herbs/{id}")
    public void putHerb(@PathVariable long id, @RequestBody HerbEntity newHerb) {
        service.updateHerb(id, newHerb);
    }
    @DeleteMapping("/herbs/{id}")
    public void deleteHerb(@PathVariable long id) {
        service.deleteHerb(id);
    }

    @DeleteMapping("/herbs/{name}")
    public void deleteHerb(@PathVariable String name) {
        service.deleteHerb(name);
    }
}
