package com.example.demo.service;

import com.example.demo.data.objects.User;
import com.example.demo.service.exceptions.NotFoundException;

import java.util.List;

public interface UserService
{
    /**
     * Returns all users
     * @return An array of all users
     */
    List<User> getAll();

    /**
     * Gets a user by their id
     * @param id The user's id
     * @return The user object
     * @throws NotFoundException If the user cannot be found
     */
    User getById(int id) throws NotFoundException;

    /**
     * Creates a new user
     * @param name The username
     * @param email The user email
     * @return The created user record
     */
    User create(String name, String email);

    /**
     * Get a user by their name
     * @param name The user's name
     * @return The user object
     * @throws NotFoundException If one matching user cannot be found
     */
    User getByName(String name) throws NotFoundException;
}
