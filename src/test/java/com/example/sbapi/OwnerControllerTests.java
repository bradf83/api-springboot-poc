package com.example.sbapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OwnerControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void getAll() throws Exception {
        this.mvc.perform(get("/owners")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void getOwner() throws Exception {
        this.mvc.perform(get("/owners/3")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void whenCreatingAnOwner() throws Exception {
        this.mvc.perform(post("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Brad\", \"lastName\": \"Test\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void whenDeleteOwner() throws Exception {
        this.mvc.perform(delete("/owners/1")).andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void whenPatchOwner() throws Exception {
        this.mvc.perform(patch("/owners/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"lastName\": \"Testers\"}"))
                .andExpect(status().isNoContent());
    }
}
