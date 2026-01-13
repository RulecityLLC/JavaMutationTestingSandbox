package com.example.demo.data;

import com.example.demo.data.objects.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest
{
    @InjectMocks
    UserRepositoryImpl instance;

    @Test
    public void handleNotFoundTest() throws Throwable
    {
        // ARRANGE

        String name = "Ed";
        String email = "ed@email.org";

        // ACT

        User actual = instance.create(name, email);

        // ASSERT

        assertEquals(name, actual.name());
        assertEquals(email, actual.email());
    }

}
