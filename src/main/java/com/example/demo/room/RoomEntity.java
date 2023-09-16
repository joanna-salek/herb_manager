package com.example.demo.room;

import com.example.demo.herb.HerbEntity;
import com.example.demo.util.WindowExposure;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "room")
public class RoomEntity {

    private @Id
    @GeneratedValue
    Long id;
    @Column(unique=true, nullable=false)
    private String name;
    private WindowExposure windowExposure;

    @ManyToMany()
    @JoinTable(name = "owned_herbs",
               joinColumns = @JoinColumn(name = "room_id"),
               inverseJoinColumns = @JoinColumn(name = "herb_id"))
    private Set<HerbEntity> herbEntitySet;

    public RoomEntity(String name, WindowExposure windowExposure) {
        this.name = name;
        this.windowExposure = windowExposure;
        this.herbEntitySet = new HashSet<>();
    }

    public RoomEntity(long id, String name, WindowExposure windowExposure) {
        this.name = name;
        this.windowExposure = windowExposure;
        this.herbEntitySet = new HashSet<>();
    }
    public RoomEntity(){
    }

    public boolean addHerb(HerbEntity herb){
        return herbEntitySet.add(herb);
    }

}
