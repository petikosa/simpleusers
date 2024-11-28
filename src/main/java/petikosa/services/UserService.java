package petikosa.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import petikosa.dtos.UserDto;
import petikosa.entities.User;
import petikosa.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(long id) {
        return convertToDto(findById(id));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).toList();
    }

    public void createUser(UserDto user) {
        userRepository.save(convertFromDto(user));
    }

    public void updateUser(UserDto user) {
        User existingUser = findById(user.id);
        existingUser.setName(user.name);
        existingUser.setUsername(user.username);
        existingUser.setPassword(user.password);
        userRepository.save(existingUser);
    }

    public void deleteUser(long id) {
        userRepository.delete(findById(id));
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getUsername(), user.getPassword());
    }

    private User convertFromDto(UserDto userDto) {
        return new User(userDto.name, userDto.username, userDto.password);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
