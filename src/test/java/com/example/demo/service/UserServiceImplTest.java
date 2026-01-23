package com.example.demo.service;

import com.example.demo.data.UserRepository;
import com.example.demo.data.objects.User;
import com.example.demo.service.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest
{
    @Mock
    UserRepository mockRepo;

    @InjectMocks
    UserServiceImpl instance;

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

        when(mockRepo.getAll()).thenReturn(expected);

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

        when(mockRepo.getById(id)).thenReturn(user1);

        // ACT

        User actual = instance.getById(id);

        // ASSERT

        assertEquals(user1, actual);
    }

    @Test
    public void getByIdExceptionTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        User user1 = new User(id, name, email);

        var ex = new RuntimeException("generic exception");

        when(mockRepo.getById(id)).thenThrow(ex);

        // ACT/ASSERT

        NotFoundException thrown = assertThrows(NotFoundException.class,
                ()-> instance.getById(id),
                "expected an exception to be thrown");
    }

    @Test
    public void createTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        User expected = new User(id, name, email);

        when(mockRepo.create(name, email)).thenReturn(expected);

        // ACT

        User actual = instance.create(name, email);

        // ASSERT

        assertEquals(expected, actual);
    }


    @Test
    public void createEmptyNameTest() throws Throwable
    {
        // ARRANGE

        String name = "";
        String email = "some@email.org";

        // ACT/ASSERT

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                ()-> instance.create(name, email),
                "expected an exception to be thrown");
    }

    @Test
    public void createEmptyEmailTest() throws Throwable
    {
        // ARRANGE

        String name = "some name";
        String email = "";

        // ACT/ASSERT

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                ()-> instance.create(name, email),
                "expected an exception to be thrown");
    }

    @Test
    public void getByNameTest() throws Throwable
    {
        // ARRANGE

        int id = 1234;
        String name = "user's name";
        String email = "some@email.org";
        User user1 = new User(id, name, email);

        List<User> expected = new ArrayList<>();
        expected.add(user1);

        when(mockRepo.getAll()).thenReturn(expected);

        // ACT

        User actual = instance.getByName(name);

        // ASSERT
    }
}
