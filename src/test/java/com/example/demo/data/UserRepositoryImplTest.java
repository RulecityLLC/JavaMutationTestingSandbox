package com.example.demo.data;

import com.example.demo.data.objects.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest
{
    @InjectMocks
    UserRepositoryImpl instance;

    @Test
    public void getAllTest() throws Throwable
    {
        // ARRANGE

        String name = "Ed";
        String email = "ed@email.org";

        // ACT

        User created = instance.create(name, email);
        List<User> actual = instance.getAll();

        // ASSERT

        assertThat(actual, hasItem(created));
    }

    @Test
    public void getByIdTest() throws Throwable
    {
        // ARRANGE

        String name = "Ed";
        String email = "ed@email.org";

        // ACT

        User created = instance.create(name, email);
        User actual = instance.getById(created.id());

        // ASSERT

        assertEquals(name, actual.name());
        assertEquals(email, actual.email());
    }

    @Test
    public void createTest() throws Throwable
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

    @Test
    public void deleteByIdTest() throws Throwable
    {
        // ARRANGE

        String name = "Ed";
        String email = "ed@email.org";

        // ACT

        User created = instance.create(name, email);
        User retrieved = instance.getById(created.id());
        instance.deleteById(created.id());

        // ASSERT

        assertThrows(IndexOutOfBoundsException.class, ()-> instance.getById(created.id()));
    }

}
