package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
   public void loadLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().
                        string(containsString("We help you make sure your plants live a happy life!")));
    }

    @Test
    public void postLogin() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/").param("username","Michelle@gmail.com").param("password","123456")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }

}
