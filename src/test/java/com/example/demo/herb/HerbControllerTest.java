package com.example.demo.herb;

import com.example.demo.util.SunExposition;
import com.example.demo.util.WateringFrequency;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HerbControllerTest {
    @MockBean
    private HerbRepository repository;
    @Autowired
    MockMvc mvc;
    private static HerbEntity cactusEntity = new HerbEntity("Cactus", SunExposition.SHADOW, WateringFrequency.ONCE_PER_DAY);
    private static HerbEntity basilEntity = new HerbEntity("Basil", SunExposition.PARTIAL_SHADOW, WateringFrequency.ONCE_PER_MONTH);


    /*
        Test GET "/herbs"
        - Mock JpaRepository findAll() to return example herb entities
        - Validate Json GET response with expected herbs
     */
    @Test
    public void getHerbs() throws Exception {
        when(repository.findAll()).thenReturn(Lists.newArrayList(cactusEntity, basilEntity));
        mvc.perform(MockMvcRequestBuilders.get("/herbs").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Cactus"))
                .andExpect(jsonPath("$[1].name").value("Basil"))
                .andExpect(jsonPath("$[0].sunExposition").value(SunExposition.SHADOW.name()))
                .andExpect(jsonPath("$[1].sunExposition").value(SunExposition.PARTIAL_SHADOW.name()))
                .andExpect(jsonPath("$[0].wateringFrequency").value(WateringFrequency.ONCE_PER_DAY.name()))
                .andExpect(jsonPath("$[1].wateringFrequency").value(WateringFrequency.ONCE_PER_MONTH.name()));
    }

    /*
        Test GET "/herbs/{name}"
          - Mock JpaRepository findByName(String name) to return example herb entity
          - Validate Json GET response with expected herb
    */
    @Test
    public void getHerbByName() throws Exception {
        when(repository.findByName(cactusEntity.getName())).thenReturn(Optional.of(cactusEntity));

        mvc.perform(MockMvcRequestBuilders.get("/herbs/Cactus").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cactus"))
                .andExpect(jsonPath("$.sunExposition").value(SunExposition.SHADOW.name()))
                .andExpect(jsonPath("$.wateringFrequency").value(WateringFrequency.ONCE_PER_DAY.name()));
    }

    /*
       Test POST "/herbs"
          - Validate Status OK when request POST "/herbs" with json entity of example herb
    */
    @Test
    public void postHerb() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/herbs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cactusEntity)))
                        .andExpect(status().isOk());
    }

    /*
       Test PUT "/herbs/{name}"
         - Mock JpaRepository findByName(String name) to return example "Cactus" herb entity
         - Validate Status OK when request PUT "/herbs/Cactus" with json entity of example herb "Basil"
    */
    @Test
    public void putHerb() throws Exception {
        when(repository.findByName(cactusEntity.getName())).thenReturn(Optional.of(cactusEntity));

        mvc.perform(MockMvcRequestBuilders.put("/herbs/Cactus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(basilEntity)))
                .andExpect(status().isOk());
    }

    /*
      Test DELETE "/herbs/{name}"
        - Mock JpaRepository findByName(String name) to return example "Cactus" herb entity
        - Validate Status OK when request DELETE "/herbs/Cactus"
   */
    @Test
    public void deleteHerb() throws Exception {
        when(repository.findByName(cactusEntity.getName())).thenReturn(Optional.of(cactusEntity));

        mvc.perform(MockMvcRequestBuilders.delete("/herbs/{name}", cactusEntity.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}