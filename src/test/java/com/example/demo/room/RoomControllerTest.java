package com.example.demo.room;

import com.example.demo.herb.HerbEntity;
import com.example.demo.herb.HerbService;
import com.example.demo.util.SunExposition;
import com.example.demo.util.WateringFrequency;
import com.example.demo.util.WindowExposure;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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
        kitchenEntity.setId(0L);
        bedroomEntity.setId(1L);
        when(repository.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<>(Lists.newArrayList(kitchenEntity, bedroomEntity), PageRequest.of(0, 20),2));
        mvc.perform(MockMvcRequestBuilders.get("/rooms").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Kitchen"))
                .andExpect(jsonPath("$[1].name").value("Bedroom"))
                .andExpect(jsonPath("$[0].windowExposure").value(WindowExposure.SOUTH.name()))
                .andExpect(jsonPath("$[1].windowExposure").value(WindowExposure.NORTH.name()));
    }

    /*
        Test GET "/rooms/{id}"
          - Mock JpaRepository findById(Long id) to return example room entity
          - Validate Json GET response with expected room
    */
    @Test
    public void getRoomById() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(kitchenEntity));

        mvc.perform(MockMvcRequestBuilders.get("/rooms/1").accept(MediaType.APPLICATION_JSON))
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
      Test PUT "/rooms/{roomId}/herbs/{herbName}"
         - Mock JpaRepository findById(Long id) to return example room entity
         - Validate Status OK when request PUT "/rooms/1/herbs/{herbName}"
   */
    @Test
    public void putHerbInRoom() throws Exception {
        HerbEntity basilEntity = new HerbEntity("Basil", SunExposition.PARTIAL_SHADOW, WateringFrequency.ONCE_PER_MONTH);
        when(repository.findById(1L)).thenReturn(Optional.of(kitchenEntity));
        when(herbService.getHerb("Basil")).thenReturn(basilEntity);

        mvc.perform(MockMvcRequestBuilders.put("/rooms/1/herbs/Basil")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
       Test PUT "/rooms/{roomId}"
         - Mock JpaRepository findById(Long id) to return example room entity
         - Validate Status OK when request PUT "/rooms/1" with json entity of example room "Kitchen"
    */
    @Test
    public void putHerb() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(kitchenEntity));

        mvc.perform(MockMvcRequestBuilders.put("/herbs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bedroomEntity)))
                .andExpect(status().isOk());
    }

    /*
      Test DELETE "/rooms/{id}"
        - Mock JpaRepository findById(Long id) to return example "Kitchen" room entity
        - Validate Status OK when request DELETE "/rooms/1"
   */
    @Test
    public void deleteRoom() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(kitchenEntity));

        mvc.perform(MockMvcRequestBuilders.delete("/herbs/1", kitchenEntity.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}