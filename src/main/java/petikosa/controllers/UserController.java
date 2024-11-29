package petikosa.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import petikosa.dtos.UserDto;
import petikosa.services.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    // private final AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("single")
    public UserDto getUserById(@Valid @NotNull  @PathParam("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping("all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("create")
    public void createUser(@Valid @RequestBody UserDto user) {
        userService.createUser(user);
    }

    @PutMapping("update")
    public void updateUser(@Valid @RequestBody UserDto user) {
        userService.updateUser(user);
    }

    @DeleteMapping("delete")
    public void deleteUser(@Valid @NotNull @PathParam("id") long id) {
        userService.deleteUser(id);
    }

//    @PostMapping("login")
//    public void login(@RequestBody LoginRequest loginRequest) {
//        Authentication authenticationRequest =
//                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
//        Authentication authenticationResponse =
//                this.authenticationManager.authenticate(authenticationRequest);
//    }
//
//    public record LoginRequest(String username, String password) {}
}
