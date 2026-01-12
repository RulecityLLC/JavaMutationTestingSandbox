package com.example.demo.service;

import com.example.demo.data.objects.User;
import com.example.demo.service.exceptions.NotFoundException;

import java.util.List;

public interface UserService
{
    List<User> getAll();

    User getById(int id) throws NotFoundException;

    User create(String name, String email);
}
