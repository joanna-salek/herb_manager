package com.example.demo.room;

import com.example.demo.herb.HerbEntity;
import com.example.demo.herb.HerbService;
import com.example.demo.util.SunExposition;
import com.example.demo.util.WateringFrequency;
import com.example.demo.util.WindowExposure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {
    @MockBean
    private RoomRepository repository;

    @MockBean
    private HerbService herbService;

    @Autowired
    MockMvc mvc;
    private static RoomEntity kitchenEntity = new RoomEntity("Kitchen", WindowExposure.SOUTH);
    private static RoomEntity bedroomEntity = new RoomEntity("Bedroom", WindowExposure.NORTH);


    /*
        Test GET "/rooms"
        - Mock JpaRepository findAll() to return example room entities
        - Validate Json GET response with expected rooms
     */
    @Test
    public void getRooms() throws Exception {
        when(repository.findAll()).thenReturn(Lists.newArrayList(kitchenEntity, bedroomEntity));
        mvc.perform(MockMvcRequestBuilders.get("/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Kitchen"))
                .andExpect(jsonPath("$[1].name").value("Bedroom"))
                .andExpect(jsonPath("$[0].windowExposure").value(WindowExposure.SOUTH.name()))
                .andExpect(jsonPath("$[1].windowExposure").value(WindowExposure.NORTH.name()));
    }

    /*
        Test GET "/rooms/{name}"
          - Mock JpaRepository findByName(String name) to return example room entity
          - Validate Json GET response with expected room
    */
    @Test
    public void getRoomByName() throws Exception {
        when(repository.findByName(kitchenEntity.getName())).thenReturn(Optional.of(kitchenEntity));

        mvc.perform(MockMvcRequestBuilders.get("/rooms/Kitchen").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kitchen"))
                .andExpect(jsonPath("$.windowExposure").value(WindowExposure.SOUTH.name()));}

    /*
       Test POST "/rooms"
          - Validate Status OK when request POST "/rooms" with json entity of example room
    */
    @Test
    public void postRoom() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(kitchenEntity)))
                .andExpect(status().isOk());
    }

    /*
      Test PUT "/rooms/{roomName}/herbs/{herbName}"
         - Mock JpaRepository findByName(String name) to return example room entity
         - Validate Status OK when request PUT "/rooms/{roomName}/herbs/{herbName}"
   */
    @Test
    public void putHerbInRoom() throws Exception {
        HerbEntity basilEntity = new HerbEntity("Basil", SunExposition.PARTIAL_SHADOW, WateringFrequency.ONCE_PER_MONTH);
        when(repository.findByName(kitchenEntity.getName())).thenReturn(Optional.of(kitchenEntity));
        when(herbService.getHerb("Basil")).thenReturn(basilEntity);

        mvc.perform(MockMvcRequestBuilders.put("/rooms/Kitchen/herbs/Basil")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
       Test PUT "/rooms/{roomName}"
         - Mock JpaRepository findByName(String name) to return example room entity
         - Validate Status OK when request PUT "/rooms/Kitchen" with json entity of example room "Kitchen"
    */
    @Test
    public void putHerb() throws Exception {
        when(repository.findByName(kitchenEntity.getName())).thenReturn(Optional.of(kitchenEntity));

        mvc.perform(MockMvcRequestBuilders.put("/herbs/Kitchen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bedroomEntity)))
                .andExpect(status().isOk());
    }

    /*
      Test DELETE "/rooms/{name}"
        - Mock JpaRepository findByName(String name) to return example "Kitchen" room entity
        - Validate Status OK when request DELETE "/rooms/Kitchen"
   */
    @Test
    public void deleteRoom() throws Exception {
        when(repository.findByName(kitchenEntity.getName())).thenReturn(Optional.of(kitchenEntity));

        mvc.perform(MockMvcRequestBuilders.delete("/herbs/{name}", kitchenEntity.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}