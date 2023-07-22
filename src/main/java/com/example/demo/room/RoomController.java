package com.example.demo.room;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService roomService) {
        this.service = roomService;
    }

    @GetMapping("/rooms")
    public List<RoomEntity> getAllRooms() {
        return service.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public RoomEntity getRoomById(@PathVariable long id) {
        return service.getRoom(id);
    }

    @GetMapping("/rooms/{name}")
    public RoomEntity getRoomByName(@PathVariable String name) {
        return service.getRoom(name);
    }

    @PostMapping("/rooms")
    public void postRoom(@RequestBody RoomEntity newRoom) {
        service.addRoom(newRoom);
    }

    @DeleteMapping("/rooms/{name}")
    public void deleteRoom(@PathVariable String roomName) {
        service.deleteRoom(roomName);
    }

    @PostMapping("rooms/{roomName}/herbs/{herbName}")
    public void postHerbInRoom(@PathVariable String roomName, @PathVariable String herbName){
        service.addHerbToRoom(roomName, herbName);
    }



}
