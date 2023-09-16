package com.example.demo.room;

import com.example.demo.util.WindowExposure;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder
@Getter
@EqualsAndHashCode
public class RoomDto {

    Long id;
    private String name;
    private WindowExposure windowExposure;

    public RoomDto(long id, String name, WindowExposure windowExposure) {
        this.id = id;
        this.name = name;
        this.windowExposure = windowExposure;
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

}
