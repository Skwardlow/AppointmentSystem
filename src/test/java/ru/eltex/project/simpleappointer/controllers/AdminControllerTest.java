package ru.eltex.project.simpleappointer.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.eltex.project.simpleappointer.dao.DateRepository;
import ru.eltex.project.simpleappointer.dao.InviteRepository;
import ru.eltex.project.simpleappointer.dao.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
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

}
