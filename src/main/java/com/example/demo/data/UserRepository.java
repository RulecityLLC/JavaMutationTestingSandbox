package com.example.demo.data;

import com.example.demo.data.objects.User;

import java.util.List;

public interface UserRepository
{
    List<User> getAll();

    User getById(int id);

    User create(String name, String email);
}
