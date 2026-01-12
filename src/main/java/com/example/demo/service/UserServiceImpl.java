package com.example.demo.service;

import com.example.demo.data.UserRepository;
import com.example.demo.data.objects.User;
import com.example.demo.service.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository repo;

    @Inject
    public UserServiceImpl(UserRepository repo)
    {
        this.repo = repo;
    }

    /**
     * Returns all users
     * @return An array of all users
     */
    @Override
    public List<User> getAll()
    {
        return repo.getAll();
    }

    /**
     * Gets a user by their id
     * @param id The user's id
     * @return The user object
     * @throws NotFoundException If the user cannot be found
     */
    @Override
    public User getById(int id) throws NotFoundException
    {
        try
        {
            return repo.getById(id);
        }
        catch (Exception ex)
        {
            throw new NotFoundException(ex);
        }
    }

    /**
     * Creates a new user
     * @param name The username
     * @param email The user email
     * @return The created user record
     */
    @Override
    public User create(String name, String email)
    {
        if (name.isEmpty()) throw new IllegalArgumentException("Name must not be empty");

        if (email.isEmpty()) throw new IllegalArgumentException("Email must not be empty");

        return repo.create(name, email);
    }
}
