package com.example.demo.room;

import com.example.demo.herb.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private static final int PAGE_SIZE = 20;
    RoomRepository repository;
    HerbService herbService;

    @Autowired
    public RoomService(RoomRepository repository, HerbService herbService) {
        this.repository = repository;
        this.herbService = herbService;
    }

    public List<RoomEntity> getAllRooms(Integer page) {
        return repository.findAll(PageRequest.of(page, PAGE_SIZE)).toList();
    }

    public List<RoomEntity> getAllRoomsWithHerbs(Integer page) {
        return repository.findAllRoomsWithHerbs(PageRequest.of(page, PAGE_SIZE));
    }

    public RoomEntity getSingleRoom(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Room don't exist with name: " + name));
    }

    public RoomEntity addRoom(RoomEntity newRoom) {
        return repository.save(newRoom);
    }

    public RoomEntity addHerbToRoom(String roomName, String herbName) {
        RoomEntity room = repository.findByName(roomName).orElseThrow(() -> new ResourceNotFoundException("Room don't exist with name: " + roomName));
        if (room.addHerb(herbService.getHerb(herbName))){
            return repository.save(room);
        }else{
            throw new IllegalArgumentException("Herb with name: " + herbName +  "already present in room " + roomName);
        }
    }

    public RoomEntity updateRoom(String roomName, RoomEntity newRoom) {
        RoomEntity oldRoom = repository.findByName(roomName).orElseThrow(() -> new ResourceNotFoundException("Room don't exist with name: " + roomName));
        oldRoom.setName(newRoom.getName());
        oldRoom.setWindowExposure(newRoom.getWindowExposure());
        return repository.save(oldRoom);
    }

    public void deleteRoom(String roomName) {
        RoomEntity room = repository.findByName(roomName).orElseThrow(() -> new ResourceNotFoundException("Room don't exist with name: " + roomName));
        repository.delete(room);
    }
}
