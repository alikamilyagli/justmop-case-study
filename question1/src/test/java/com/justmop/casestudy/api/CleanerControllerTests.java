package com.justmop.casestudy.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justmop.casestudy.api.entity.sql.Cleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CleanerControllerTests extends CaseStudyApiApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createCleaner() throws Exception {

        Cleaner cleaner = new Cleaner();
        cleaner.setFirstName("test");
        cleaner.setLastName("test");

        mockMvc.perform(post("/api/cleaner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cleaner)))
                .andExpect(status().isOk());
    }

    @Test
    public void retrieveCleanerList() throws Exception {

        int page = 0;
        int size = 10;

        mockMvc.perform(get("/api/cleaner?page="+page+"&size="+size))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.size").value(size))
                .andExpect(jsonPath("$.number").value(page))
                .andExpect(jsonPath("$.empty").value(false))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.numberOfElements").value(size));

    }
}
