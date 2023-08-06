package com.example.demo.herb;

import com.example.demo.util.SunExposition;
import com.example.demo.util.WateringFrequency;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "herb")
public class HerbEntity {
    private @Id @GeneratedValue
    Long id;
    @Column(unique=true, nullable=false)
    private String name;
    private SunExposition sunExposition;
    private WateringFrequency wateringFrequency;

    public HerbEntity(String name, SunExposition sunExposition, WateringFrequency wateringFrequency) {
        this.name = name;
        this.sunExposition = sunExposition;
        this.wateringFrequency = wateringFrequency;
    }

    public HerbEntity(){}

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

    public SunExposition getSunExposition() {
        return sunExposition;
    }

    public void setSunExposition(SunExposition sunExposition) {
        this.sunExposition = sunExposition;
    }

    public WateringFrequency getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(WateringFrequency wateringFrequency) {
        this.wateringFrequency = wateringFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HerbEntity herb = (HerbEntity) o;
        return Objects.equals(id, herb.id) && Objects.equals(name, herb.name) && sunExposition == herb.sunExposition && wateringFrequency == herb.wateringFrequency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sunExposition, wateringFrequency);
    }

    @Override
    public String toString() {
        return "Herb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sunExposition=" + sunExposition +
                ", wateringFrequency=" + wateringFrequency +
                '}';
    }
}
