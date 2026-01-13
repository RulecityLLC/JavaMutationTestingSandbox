package com.example.demo.controller;

import com.example.demo.data.objects.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
    @Mock
    UserService mockService;

    @InjectMocks
    UserController instance;

    @Test
    public void getAllTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        User user1 = new User(id, name, email);

        List<User> expected = new ArrayList<>();
        expected.add(user1);

        when(mockService.getAll()).thenReturn(expected);

        // ACT

        List<User> actual = instance.getAll();

        // ASSERT

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdHappyPathTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        User user1 = new User(id, name, email);

        when(mockService.getById(id)).thenReturn(user1);

        // ACT

        User actual = instance.getUserById(id);

        // ASSERT

        assertEquals(user1, actual);
    }

    @Test
    public void createUserTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        var userToCreate = new UserWithoutId(name, email);
        User user1 = new User(id, name, email);

        when(mockService.create(name, email)).thenReturn(user1);

        // ACT

        User actual = instance.createUser(userToCreate);

        // ASSERT

        assertEquals(user1, actual);
    }

}
