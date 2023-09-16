package com.example.demo.herb;

import com.example.demo.util.SunExposition;
import com.example.demo.util.WateringFrequency;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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
}
