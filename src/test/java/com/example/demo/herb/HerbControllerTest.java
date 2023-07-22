package com.example.demo.herb;

import com.example.demo.util.SunExposition;
import com.example.demo.util.WateringFrequency;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.text.html.Option;

import java.util.Optional;
import java.util.OptionalInt;

import static org.mockito.Mockito.doReturn;
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

    @Test
    public void getALlHerbs() throws Exception {
        HerbEntity herb1 = new HerbEntity("Cactus", SunExposition.SHADOW, WateringFrequency.ONCE_PER_DAY);
        HerbEntity herb2 = new HerbEntity("Basil", SunExposition.PARTIAL_SHADOW, WateringFrequency.ONCE_PER_MONTH);
        doReturn(Lists.newArrayList(herb1, herb2)).when(repository).findAll();

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

    @Test
    public void getALlHerbB() throws Exception {
        HerbEntity herb1 = new HerbEntity("Cactus", SunExposition.SHADOW, WateringFrequency.ONCE_PER_DAY);
        when(repository.findByName("Cactus")).thenReturn(Optional.of(herb1));
//        doReturn(Optional.of(herb1)).when(repository.findById(1L));

        mvc.perform(MockMvcRequestBuilders.get("/herbs/Cactus").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.name").value("Cactus"))
                .andExpect(jsonPath("$.sunExposition").value(SunExposition.SHADOW.name()))
                .andExpect(jsonPath("$.wateringFrequency").value(WateringFrequency.ONCE_PER_DAY.name()));

    }
}