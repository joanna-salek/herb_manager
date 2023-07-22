package com.example.demo.room;

import com.example.demo.herb.HerbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository  extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByName(String name);
}
