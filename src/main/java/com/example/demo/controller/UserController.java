package com.example.demo.controller;

import com.example.demo.data.objects.User;
import com.example.demo.service.UserService;
import com.example.demo.service.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService svc;

    @Inject
    public UserController(UserService svc)
    {
        this.svc = svc;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content) })
    @GetMapping(value = "", produces = "application/json")
    public List<User> getAll()
    {
        return svc.getAll();
    }

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping(value = "/{userId}", produces = "application/json")
    public User getUserById(@Parameter(description = "The user id") @PathVariable("userId") int userId) throws NotFoundException
    {
        return svc.getById(userId);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content) })
    @PostMapping(value = "", produces = "application/json")
    public User createUser(@Parameter(description = "Details about the user") @RequestBody UserWithoutId user)
    {
        return svc.create(user.name(), user.email());
    }

}
