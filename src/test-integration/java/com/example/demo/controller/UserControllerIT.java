package com.example.demo.controller;

import com.example.demo.data.objects.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * This is an example of an integration test that tests the controller while mocking out the controller's dependencies.
 * It's better than a full @SpringBootTest because it executes faster.
 * See <a href="https://spring.io/guides/gs/testing-web">...</a>
 */
@WebMvcTest(UserController.class)
@AutoConfigureRestTestClient
public class UserControllerIT
{
    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private UserService mockService;

    @Test
    public void getAllTest() throws Throwable
    {
        User user1 = new User(1234, "user name", "user@email.org");
        List<User> lstAll = new ArrayList<>();
        lstAll.add(user1);

        when(mockService.getAll()).thenReturn(lstAll);

        this.restTestClient.get().uri("/users")
                .exchange()
                .expectBody(String.class)
                .isEqualTo("[{\"id\":1234,\"name\":\"user name\",\"email\":\"user@email.org\"}]");
    }
}
