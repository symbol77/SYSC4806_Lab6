package com.example.lab5;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestingWebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void setup() throws Exception {
        this.mockMvc.perform(post("/books/newBook"))
                .andDo(print()).andExpect(status().isOk());

        BuddyInfo buddy = new BuddyInfo("Bob", "123", "456");
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/books/{id}/addBuddy", 1)
                .content(asJsonString(buddy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addressBookShouldReturnMessage() throws Exception {
        this.mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Bob")));
    }

    @Test
    public void addBuddy() throws Exception {
        this.mockMvc.perform(post("/books/newBook"))
                .andDo(print()).andExpect(status().isOk());

        BuddyInfo buddy = new BuddyInfo("Carl", "123", "456");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/books/{id}/addBuddy", 1)
                .content(asJsonString(buddy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Carl")));
    }

    @Test
    public void deleteBuddy() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/books/removeBuddy/{id}", 1)
                .param("buddyID", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Removed buddy")));
    }

    @Test
    public void findSpecificAddressBook() throws Exception {
        this.mockMvc.perform(get("/books/getBook/{id}", "1")).andDo(print()).andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
