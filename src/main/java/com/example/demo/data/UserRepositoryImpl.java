package com.example.demo.data;

import com.example.demo.data.objects.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class intentionally stubs out real database calls to focus more on showing how to write testable code
 */
@Repository
public class UserRepositoryImpl implements UserRepository
{
    List<User> users = new ArrayList<>();

    public UserRepositoryImpl()
    {
        User user = new User(0, "Alice", "alice@example.com");
        users.add(user);

        user = new User(1, "Bob", "bob@example.com");
        users.add(user);
    }

    @Override
    public List<User> getAll()
    {
        return users;
    }

    @Override
    public User getById(int id)
    {
        return users.get(id);
    }

    @Override
    public User create(String name, String email)
    {
        User newUser = new User(users.size(), name, email);
        users.add(newUser);
        return newUser;
    }
}
