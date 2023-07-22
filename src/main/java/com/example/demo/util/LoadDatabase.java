package com.example.demo.util;

import com.example.demo.herb.HerbEntity;
import com.example.demo.herb.HerbRepository;
import com.example.demo.room.RoomEntity;
import com.example.demo.room.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase1(HerbRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new HerbEntity("Nagietek", SunExposition.PARTIAL_SUN, WateringFrequency.ONCE_PER_WEEK)));
            log.info("Preloading " + repository.save(new HerbEntity("Bazylia", SunExposition.FULL_SUN, WateringFrequency.ONCE_PER_DAY)));
        };
    }
    @Bean
    CommandLineRunner initDatabase2(RoomRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new RoomEntity("Salon", WindowExposure.NORTH)));
            log.info("Preloading " + repository.save(new RoomEntity("Sypialnia", WindowExposure.SOUTH)));
        };
    }


}
