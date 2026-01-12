package com.example.demo.data.objects;

/**
 * To pass user data between classes
 *
 * @param id    User's id
 * @param name  User's name
 * @param email User's email
 */
public record User(int id, String name, String email)
{
}
