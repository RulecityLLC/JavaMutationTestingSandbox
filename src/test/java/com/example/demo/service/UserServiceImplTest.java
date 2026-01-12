package com.example.demo.service;

import com.example.demo.data.UserRepository;
import com.example.demo.data.objects.User;
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

}
