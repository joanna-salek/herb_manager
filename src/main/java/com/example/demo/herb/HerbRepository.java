package com.example.demo.herb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HerbRepository extends JpaRepository<HerbEntity, Long> {
    Optional<HerbEntity> findByName(String name);
}
