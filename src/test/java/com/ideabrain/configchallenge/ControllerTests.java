package com.ideabrain.configchallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideabrain.configchallenge.dtos.Request;
import com.ideabrain.configchallenge.services.ConfigsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigsService configsService;

    List<Request> requestsList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    String requestString = "{\"name\":\"datacenter-1\",\"metadata\":{\"monitoring\":{\"enabled\":true},\"limits\":{\"cpu\":{\"enabled\":false,\"value\":\"700m\"}}}}";


    @Test
    public void testGetNoConfigs() throws Exception {
        mockMvc.perform(get("/configs"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetListInsertConfigs() throws Exception {

        Request request = objectMapper.readValue(requestString, Request.class);
        requestsList.add(request);


        when(configsService.fetchAllConfigs()).thenReturn(requestsList);
        mockMvc.perform(get("/configs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("datacenter-1"))
                .andExpect(jsonPath("$[0].metadata.monitoring.enabled").value("true"))
                .andExpect(jsonPath("$[0].metadata.limits.cpu.enabled").value("false"))
                .andExpect(jsonPath("$[0].metadata.limits.cpu.value").value("700m"));
    }


    @Test
    public void testCreateConfig() throws Exception {
        Request request = objectMapper.readValue(requestString, Request.class);

        when(configsService.createConfig(request)).thenReturn(request);
        mockMvc.perform(post("/configs").contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("datacenter-1"))
                .andExpect(jsonPath("$.metadata.monitoring.enabled").value("true"))
                .andExpect(jsonPath("$.metadata.limits.cpu.enabled").value("false"))
                .andExpect(jsonPath("$.metadata.limits.cpu.value").value("700m"));

    }

    @Test
    public void getConfigByName() throws Exception {

        Request request = objectMapper.readValue(requestString, Request.class);
        requestsList.add(request);


        when(configsService.fetchConfigByName("datacenter-1")).thenReturn(request);
        mockMvc.perform(get("/configs/datacenter-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("datacenter-1"))
                .andExpect(jsonPath("$.metadata.monitoring.enabled").value("true"))
                .andExpect(jsonPath("$.metadata.limits.cpu.enabled").value("false"))
                .andExpect(jsonPath("$.metadata.limits.cpu.value").value("700m"));

    }

}
