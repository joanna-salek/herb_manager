package com.example.demo.room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDtoMapper {

    private RoomDtoMapper(){}

    public static List<RoomDto> mapToRoomDtos(List<RoomEntity> allRooms) {
        return allRooms.stream().map(room -> mapToRoomDto(room)).collect(Collectors.toList());
    }

    public static RoomDto mapToRoomDto(RoomEntity room) {
        return RoomDto.builder().id(room.getId()).name(room.getName()).windowExposure(room.getWindowExposure()).build();
    }
}
