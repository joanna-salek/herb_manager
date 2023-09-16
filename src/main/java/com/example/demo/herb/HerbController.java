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
    public List<HerbEntity> getHerbs(@RequestParam(required = false) Integer page) {
        Integer pageNum = page != null && page > 0 ? page : 0;
        return service.getHerbs(pageNum);
    }

    @GetMapping("/herbs/{name}")
    public HerbEntity getHerbByName(@PathVariable String name) {
        return service.getHerb(name);
    }

    @PostMapping("/herbs")
    public HerbEntity postHerb(@RequestBody HerbEntity newHerb) {
        return service.addHerb(newHerb);
    }

    @PutMapping("/herbs/{name}")
    public HerbEntity putHerb(@PathVariable String name, @RequestBody HerbEntity updateHerb) {
        return service.updateHerb(name, updateHerb);
    }

    @DeleteMapping("/herbs/{name}")
    public void deleteHerb(@PathVariable String name) {
        service.deleteHerb(name);
    }
}