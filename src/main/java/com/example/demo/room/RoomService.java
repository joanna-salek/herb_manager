package com.example.demo.room;

import com.example.demo.herb.HerbEntity;
import com.example.demo.herb.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    RoomRepository repository;

    HerbService herbService;

    @Autowired
    public RoomService(RoomRepository repository, HerbService herbService) {
        this.repository = repository;
        this.herbService = herbService;
    }

    public List<RoomEntity> getAllRooms() {
        return repository.findAll();
    }

    public RoomEntity getRoom(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Room not exist with name: " + name));
    }

    public void addRoom(RoomEntity newRoom) {
        repository.save(newRoom);
    }

    public void addHerbToRoom(String roomName, String herbName) {
        RoomEntity room = repository.findByName(roomName).orElseThrow(() -> new ResourceNotFoundException("Room not exist with name: " + roomName));
        room.addHerb(herbService.getHerb(herbName));
        repository.save(room);
    }

    public void deleteRoom(String roomName) {
        RoomEntity room = repository.findByName(roomName).orElseThrow(() -> new ResourceNotFoundException("Room not exist with name: " + roomName));
        repository.delete(room);

    }

    public void deleteRoom(long roomId) {
        RoomEntity room = repository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not exist with id: " + roomId));
        repository.delete(room);

    }

    public void updateRoom(String roomName, RoomEntity updateRoom) {
        RoomEntity newRoom = repository.findByName(roomName).orElseThrow(() -> new ResourceNotFoundException("Room not exist with name: " + roomName));

        newRoom.setName(updateRoom.getName());
        newRoom.setWindowExposure(updateRoom.getWindowExposure());

        repository.save(newRoom);
    }
}
