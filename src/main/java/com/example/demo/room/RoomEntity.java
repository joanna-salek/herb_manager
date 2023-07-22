package com.example.demo.room;

import com.example.demo.herb.HerbEntity;
import com.example.demo.util.WindowExposure;
import jakarta.persistence.*;

import java.util.*;

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
    public RoomEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindowExposure getWindowExposure() {
        return windowExposure;
    }

    public void setWindowExposure(WindowExposure windowExposure) {
        this.windowExposure = windowExposure;
    }

    public Set<HerbEntity> getHerbEntitySet() {
        return herbEntitySet;
    }

    public void setHerbEntitySet(Set<HerbEntity> herbEntitySet) {
        this.herbEntitySet = herbEntitySet;
    }

    public void addHerb(HerbEntity herb){
        herbEntitySet.add(herb);
    }

    public void addHerbs(List<HerbEntity> herbs){
        herbs.forEach(this::addHerb);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && windowExposure == that.windowExposure && Objects.equals(herbEntitySet, that.herbEntitySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, windowExposure, herbEntitySet);
    }
}
