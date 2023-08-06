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
        return service.getHerbs();
    }

    @GetMapping("/herbs/{name}")
    public HerbEntity getHerbByName(@PathVariable String name) {
        return service.getHerb(name);
    }

    @PostMapping("/herbs")
    public void postHerb(@RequestBody HerbEntity newHerb) {
        service.addHerb(newHerb);
    }

    @PutMapping("/herbs/{name}")
    public void putHerb(@PathVariable String name, @RequestBody HerbEntity updateHerb) {
        service.updateHerb(name, updateHerb);
    }
    @DeleteMapping("/herbs/{name}")
    public void deleteHerb(@PathVariable String name) {
        service.deleteHerb(name);
    }
}
