package com.jitterted.ebp.blackjack.adapter.in.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testHomePageReturns200Ok() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk());
    }

    @Test
    void testPostStartGameReturns200Ok() throws Exception {
        mockMvc.perform(post("/start-game"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/game"));
    }

    @Test
    void testGetGameReturn200Ok() throws Exception {
        mockMvc.perform(get("/game"))
               .andExpect(status().isOk());
    }
}
