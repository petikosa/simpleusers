package petikosa.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import petikosa.dtos.UserDto;
import petikosa.services.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/public/single")
    public UserDto getUserById(@Valid @NotNull  @PathParam("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/public/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/auth/create")
    public void createUser(@Valid @RequestBody UserDto user) {
        userService.createUser(user);
    }

    @PutMapping("/auth/update")
    public void updateUser(@Valid @RequestBody UserDto user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/auth/delete")
    public void deleteUser(@Valid @NotNull @PathParam("id") long id) {
        userService.deleteUser(id);
    }

}
