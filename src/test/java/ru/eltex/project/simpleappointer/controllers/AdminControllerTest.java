package ru.eltex.project.simpleappointer.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.eltex.project.simpleappointer.dao.DateRepository;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private MockMvc mockMvc;

    private InviteRepository inviteRepository;
    private DateRepository dateRepository;
    private UserRepository userRepository;

    @Test
    public void contextLoad() {
        org.assertj.core.api.Assertions.assertThat(adminController).isNotNull();
    }

    @Test
    public void register() throws Exception {
        this.mockMvc.perform().andDo()
    }
}
