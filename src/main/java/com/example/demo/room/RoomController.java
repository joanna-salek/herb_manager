package com.example.demo.room;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.room.RoomDtoMapper.mapToRoomDtos;

@Component
@RestController
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService roomService) {
        this.service = roomService;
    }

    @GetMapping("/rooms")
    public List<RoomDto> getRooms(@RequestParam(required = false) Integer page) {
        Integer pageNum = page != null && page > 0 ? page : 0;
        return mapToRoomDtos(service.getAllRooms(pageNum));
    }

    @GetMapping("/rooms/herbs")
    public List<RoomEntity> getRoomsWithHerbs(@RequestParam(required = false) Integer page) {
        Integer pageNum = page != null && page > 0 ? page : 0;
        return service.getAllRoomsWithHerbs(pageNum);
    }

    @GetMapping("/rooms/{name}")
    public RoomEntity getRoomByName(@PathVariable String name) {
        return service.getSingleRoom(name);
    }

    @PostMapping("/rooms")
    public RoomEntity postRoom(@RequestBody RoomEntity newRoom) {
        return service.addRoom(newRoom);
    }

    @PutMapping("rooms/{roomName}/herbs/{herbName}")
    public RoomEntity postHerbInRoom(@PathVariable String roomName, @PathVariable String herbName){
        return service.addHerbToRoom(roomName, herbName);
    }

    @PutMapping("rooms/{roomName}")
    public RoomEntity putRoom(@PathVariable String roomName, @RequestBody RoomEntity newRoom){
        return service.updateRoom(roomName, newRoom);
    }

    @DeleteMapping("/rooms/{name}")
    public void deleteRoom(@PathVariable String roomName) {
        service.deleteRoom(roomName);
    }

}
