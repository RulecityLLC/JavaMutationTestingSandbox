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

    @Override
    public List<User> getAll()
    {
        return repo.getAll();
    }

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

    @Override
    public User create(String name, String email)
    {
        if (name.isEmpty()) throw new IllegalArgumentException("Name must not be empty");

        if (email.isEmpty()) throw new IllegalArgumentException("Email must not be empty");

        return repo.create(name, email);
    }

    @Override
    public User getByName(String name) throws NotFoundException
    {
        List<User> all = repo.getAll();
        List<User> result = all.stream().filter(m -> m.name().equals(name)).toList();
        if (result.isEmpty())
        {
            throw new NotFoundException(null);
        }
        if (result.size() != 1)
        {
            throw new RuntimeException("More than one user has that name!");
        }
        return result.getFirst();
    }
}
