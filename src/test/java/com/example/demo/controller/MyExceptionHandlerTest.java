package com.example.demo.controller;

import com.example.demo.service.exceptions.NotFoundException;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MyExceptionHandlerTest
{
    @InjectMocks
    MyExceptionHandler instance;

    @Test
    public void handleNotFoundTest() throws Throwable
    {
        // ARRANGE

        String msg = "some msg";
        var ex = new NotFoundException(new RuntimeException(msg));

        // ACT

        ResponseEntity<@NonNull String> actual = instance.handleNotFound(ex);

        // ASSERT

        assertThat(actual.getBody(), containsString(msg));
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }

}
