package com.example.demo.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository  extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByName(String name);

    @Query("Select r From RoomEntity r Left Join r.herbEntitySet h")
    List<RoomEntity> findAllRoomsWithHerbs(Pageable page);
}
