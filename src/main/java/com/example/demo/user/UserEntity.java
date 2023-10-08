package com.example.demo.user;

import com.example.demo.room.RoomEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "userTable")
public class UserEntity {
    private @Id
    @GeneratedValue
    Long id;
    @Column(nullable=false)
    private String password;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String city;

    @ManyToMany()
    @JoinTable(name = "user_rooms",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<RoomEntity> roomEntitySet;

    public UserEntity(String password, String email, String firstName, String lastName, String city) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.roomEntitySet = new HashSet<>();
    }

    public UserEntity(String email, String firstName, String lastName, String city) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.roomEntitySet = new HashSet<>();
    }
    public UserEntity(){
    }

    public boolean addHerb(RoomEntity room){
        return roomEntitySet.add(room);
    }

}
