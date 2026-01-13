package com.example.demo.controller;

/**
 * To create a new user
 *
 * @param name  User's name
 * @param email User's email
 */
public record UserWithoutId(String name, String email)
{
}
